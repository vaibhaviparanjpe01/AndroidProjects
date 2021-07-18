package com.example.clientapp;
import Utility.ClientData;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.TextView;
import com.example.clientapp.Utility.ClientData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;

    ArrayList<ClientData> StoreContacts ;

    Client(String addr, int port, TextView textResponse,ArrayList<ClientData> contracts) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
        this.StoreContacts = contracts;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            //       ClientData x = new ClientData();
            //     x.email="ss@ss.com";
            //   x.name = "ss";
            // x.mobile = "345345345";
            ObjectOutputStream ois =
                    new ObjectOutputStream(socket.getOutputStream());
            ois.writeObject(StoreContacts);
            ois.close();
            socket.close();
            response = "good";
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

}