package com.example.cameraexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import android.content.*;
import android.view.*;


public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST=1888;
    ImageView img;
Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.imageView);
       Button b1 =findViewById(R.id.button);
      b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(0,0,null);
        if(requestCode==CAMERA_REQUEST)
        {
            Bitmap photo=(Bitmap)data.getExtras().get("data");
            img.setImageBitmap(photo);
        }
    }
}
