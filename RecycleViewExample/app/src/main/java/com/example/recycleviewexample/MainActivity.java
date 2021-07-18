package com.example.recycleviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RecyclerView classList;
    LinearLayoutManager linearLayoutManager;
    ProgressDialog progressDialog;
    FloatingActionButton fab;
    private Uri resultUri;
    private ImageView profileImage;
    DatabaseReference ref;
    RadioGroup secondField;
    String days = "";


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classList = findViewById(R.id.classes);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        classList.setLayoutManager(linearLayoutManager);
        classList.setHasFixedSize(true);
        fab = findViewById(R.id.add_class_btn);

        getSupportActionBar().setTitle("Batches - Sameer Sir Classes");
        ref = FirebaseDatabase.getInstance().getReference().child("Batches");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 showAddItemDialog();
            }
        });
        loadList();


    }

    private class ItemViewHolder1 extends RecyclerView.ViewHolder {
        View mView;

        public ItemViewHolder1(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setBatchName(String item) {
            TextView book_name = mView.findViewById(R.id.className);
            book_name.setText(item);

        }

        public void setBatchDays(String item) {
            TextView book_name = mView.findViewById(R.id.class_days_text);
            book_name.setText("" + item);

        }

        public void setBatchTimeOne(String item) {
            TextView book_name = mView.findViewById(R.id.classTiming1_text);
            book_name.setText(item);

        }

        public void setBatchTimeTwo(String item) {
            TextView book_name = mView.findViewById(R.id.classTiming2_text);
            book_name.setText(item);

        }

        public void setBatchTimeThree(String item) {
            TextView book_name = mView.findViewById(R.id.classTiming3_text);
            book_name.setText(item);

        }


        public void setItemImage(String item, Context context) {
            ImageView itemImage = mView.findViewById(R.id.imageClass);
            Glide.with(context).load(item).into(itemImage);
        }


    }
    private void loadList() {

        FirebaseRecyclerOptions<ClassInfoModel> options =
                new FirebaseRecyclerOptions.Builder<ClassInfoModel>()
                        .setQuery(ref, ClassInfoModel.class)
                        .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<ClassInfoModel, ItemViewHolder1>(options) {
            @Override
            public ItemViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.class_single_layout, parent, false);

                return new ItemViewHolder1(view);
            }

            @Override
            protected void onBindViewHolder(ItemViewHolder1 holder, int position, final ClassInfoModel model) {


                final String id =getRef(position).getKey();

                holder.setBatchName(model.getBatchName());
                holder.setBatchDays(model.getBatchDays());
                holder.setBatchTimeOne(model.getBatchTime1());
                holder.setBatchTimeTwo(model.getBatchTime2());
                holder.setBatchTimeThree(model.getBatchTime3());
                holder.setItemImage(model.getBatchImage(),getApplicationContext());

                holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                            showEditItemDialog(model.getBatchName(),
                                    model.getBatchDays(),
                                    model.getBatchTime1(),
                                    model.getBatchTime2(),
                                    model.getBatchTime3(),
                                    model.getBatchImage(),
                                    id);

                        return false;
                    }
                });

            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                progressDialog.dismiss();

            }
        };
        adapter.startListening();
        classList.setAdapter(adapter);
    }


    private void showAddItemDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add Batch");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addBooks = inflater.inflate(R.layout.add_class_dialog,null);
        final TextInputEditText fieldFirst = addBooks.findViewById(R.id.batchName);
        secondField = addBooks.findViewById(R.id.radioDays);
        final TextInputEditText thirdField = addBooks.findViewById(R.id.classTiming1);
        final TextInputEditText fourField = addBooks.findViewById(R.id.classTiming2);
        final TextInputEditText fiveField = addBooks.findViewById(R.id.classTiming3);



        fieldFirst.setHint("Batch Name");
        thirdField.setHint("Batch Timings 1");
        fourField.setHint("Batch Timings 2");
        fiveField.setHint("Batch Timings 3");

        final Button saveBtn = addBooks.findViewById(R.id.batchSaveBtn);
        final Button cancelBtn = addBooks.findViewById(R.id.batchCancelBtn);
        profileImage = addBooks.findViewById(R.id.subjectImage);


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        dialog.setCancelable(false);
        dialog.setView(addBooks);


        final AlertDialog alertDialog = dialog.create();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(fieldFirst.getText().toString())
                        && !TextUtils.isEmpty(thirdField.getText().toString())
                        && !TextUtils.isEmpty(fourField.getText().toString())
                        && !TextUtils.isEmpty(fiveField.getText().toString())
                        && resultUri != null && !Uri.EMPTY.equals(resultUri)){
                    progressDialog.show();

                    switch (secondField.getCheckedRadioButtonId())
                    {
                        case R.id.radioMwf:
                            days="Monday, Wednesday, Friday";
                            break;
                        case R.id.radioTts:
                            days="Tuesday, Thursday, Saturday";
                            break;
                        default:
                            days = "Not defined";

                    }

                    final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Batches")
                            .child(resultUri.getLastPathSegment());

                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                    byte[] data = baos.toByteArray();
                    UploadTask uploadTask = filePath.putBytes(data);

                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return filePath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                HashMap hm = new HashMap();
                                hm.put("batchName",fieldFirst.getText().toString());
                                hm.put("batchDays",days);
                                hm.put("batchTime1",thirdField.getText().toString());
                                hm.put("batchTime2",fourField.getText().toString());
                                hm.put("batchTime3",fiveField.getText().toString());
                                hm.put("batchImage",String.valueOf(downloadUri));
                                String key =  ref.push().getKey();

                                ref.child(key).updateChildren(hm).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(Task task) {
                                        if (task.isSuccessful()){
                                            progressDialog.dismiss();
                                            alertDialog.dismiss();
                                            resultUri =null;
                                            Toast.makeText(getApplicationContext(), "Added!!", Toast.LENGTH_SHORT).show();
                                        }else {
                                            progressDialog.dismiss();
                                            alertDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Oops/"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                progressDialog.dismiss();
                                alertDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Oops/"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }
    private void deleteDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete this item?");
        builder.setCancelable(false);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ref.child(id).setValue(null);
                dialogInterface.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            profileImage.setImageURI(resultUri);
        }
    }

    private void showEditItemDialog(String name, String day, String time1, String time2,String time3, String image,final String key) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Edit Stars");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addBooks = inflater.inflate(R.layout.add_class_dialog,null);

        final TextInputEditText fieldFirst = addBooks.findViewById(R.id.batchName);

        final TextInputEditText thirdField = addBooks.findViewById(R.id.classTiming1);

        final TextInputEditText fourField = addBooks.findViewById(R.id.classTiming2);

        final TextInputEditText fiveField = addBooks.findViewById(R.id.classTiming3);

        fieldFirst.setHint("Batch Name");
        thirdField.setHint("Batch Timings 1");
        fourField.setHint("Batch Timings 2");
        fiveField.setHint("Batch Timings 3");

        fieldFirst.setText(name);
        thirdField.setText(time1);
        fourField.setText(time2);
        fiveField.setText(time3);

        ImageView deleteBtn = addBooks.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog(key);
            }
        });

        final Button saveBtn = addBooks.findViewById(R.id.batchSaveBtn);
        final Button cancelBtn = addBooks.findViewById(R.id.batchCancelBtn);
        profileImage = addBooks.findViewById(R.id.subjectImage);
        secondField = addBooks.findViewById(R.id.radioDays);
        Glide.with(getApplicationContext()).load(image).into(profileImage);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        dialog.setCancelable(false);
        dialog.setView(addBooks);


        final AlertDialog alertDialog = dialog.create();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(fieldFirst.getText().toString())
                        && !TextUtils.isEmpty(thirdField.getText().toString()) && resultUri != null && !Uri.EMPTY.equals(resultUri)
                ){
                    progressDialog.show();

                    switch (secondField.getCheckedRadioButtonId())
                    {
                        case R.id.radioMwf:
                            days="Monday, Wednesday, Friday";
                            break;
                        case R.id.radioTts:
                            days="Tuesday, Thursday, Saturday";
                            break;
                        default:
                            days = "Not defined";

                    }

                    final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("batches").child(resultUri.getLastPathSegment());

                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                    byte[] data = baos.toByteArray();
                    UploadTask uploadTask = filePath.putBytes(data);

                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return filePath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                HashMap hm = new HashMap();
                                hm.put("batchname",fieldFirst.getText().toString());
                                hm.put("batchdays",days);
                                hm.put("classtime1",thirdField.getText().toString());
                                hm.put("classtime2",fourField.getText().toString());
                                hm.put("classtime3",fiveField.getText().toString());
                                hm.put("imageclass",String.valueOf(downloadUri));
                                String key =  ref.push().getKey();

                                ref.child(key).updateChildren(hm).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(Task task) {
                                        if (task.isSuccessful()){
                                            progressDialog.dismiss();
                                            alertDialog.dismiss();
                                            resultUri =null;
                                            Toast.makeText(getApplicationContext(), "Edited!!", Toast.LENGTH_SHORT).show();
                                        }else {
                                            progressDialog.dismiss();
                                            alertDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Oops/"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                progressDialog.dismiss();
                                alertDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Oops/"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else if (!TextUtils.isEmpty(fieldFirst.getText().toString())
                        && !TextUtils.isEmpty(thirdField.getText().toString())){

                    switch (secondField.getCheckedRadioButtonId())
                    {
                        case R.id.radioMwf:
                            days="Monday, Wednesday, Friday";
                            break;
                        case R.id.radioTts:
                            days="Tuesday, Thursday, Saturday";
                            break;
                        default:
                            days = "Not defined";

                    }
                    HashMap hm = new HashMap();
                    hm.put("batchName",fieldFirst.getText().toString());
                    hm.put("batchDays",days);
                    hm.put("batchTime1",thirdField.getText().toString());
                    hm.put("batchTime2",fourField.getText().toString());
                    hm.put("batchTime3",fiveField.getText().toString());


                    ref.child(key).updateChildren(hm).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(Task task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                alertDialog.dismiss();
                                resultUri =null;
                                Toast.makeText(getApplicationContext(), "Edited!!", Toast.LENGTH_SHORT).show();
                            }else {
                                progressDialog.dismiss();
                                alertDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Oops/"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }

}