package com.example.texttospeech;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;
import java.util.Locale;
import android.widget.*;
import android.view.*;


public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText t1;
    TextToSpeech tt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.editText);
        tt1 = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
                {
                if (status != TextToSpeech.ERROR) {
                tt1.setLanguage(Locale.UK);
                }
            }
        });


        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak = t1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tt1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
}
