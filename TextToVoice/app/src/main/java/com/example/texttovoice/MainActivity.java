package com.example.texttovoice;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.view.*;
import android.widget.*;
import java.util.Locale;
import android.speech.SpeechRecognizer;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    EditText t1;
    Button b1;
    TextToSpeech tts1;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(EditText)findViewById(R.id.editTextTextMultiLine);
        tts1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR)
                {
                    tts1.setLanguage(Locale.UK);
                }
            }
        });
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = t1.getText().toString();
                Toast.makeText(getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
                tts1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);

            }
        });
    }
}