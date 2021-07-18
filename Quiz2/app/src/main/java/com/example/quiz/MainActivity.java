package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.*;
import android.view.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

                result();
                cnt++;
                showQ();
                b1.setText("Finish");
            }

        }
        /*
        else
        {
            result();
            setContentView(R.layout.result);
            TextView rs = (TextView) findViewById(R.id.textView4);
            String msg = "Dear" + Name + "Your marks is" + result + "out of 5";
            rs.setText(msg);
        }

         */
    }


    public void showQ() {
        qTxt.setText(Q[cnt]);
        r0.setText(choice[cnt][0]);
        r1.setText(choice[cnt][1]);
        r2.setText(choice[cnt][2]);
        r3.setText(choice[cnt][3]);
        rb1.clearCheck();
    }

    public void result() {
        if (r0.isChecked()) {
            uAns = "A";
        }
        if (r1.isChecked()) {
            uAns = "B";
        }
        if (r2.isChecked()) {
            uAns = "C";
        }
        if (r3.isChecked()) {
            uAns = "D";
        }
        if (uAns.equals(ans[cnt])) {
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            result++;
        } else {
            Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
        }
    }

    public void exitclick(View v) {
        finish();
    }
}