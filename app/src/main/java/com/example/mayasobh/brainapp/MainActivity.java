package com.example.mayasobh.brainapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timeTV;
    Button playAgainBtn;
    CountDownTimer cnt = new CountDownTimer(30100, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            String seconds;
            int sec = (int)millisUntilFinished/1000;

            seconds = Integer.toString(sec);

            if(sec<10)
            {
                timeTV.setText("0"+ seconds + " sec");
            }
            else
            {
                timeTV.setText(seconds + " sec");
            }
        }

        @Override
        public void onFinish() {
            timeTV.setText("00" + " sec");
            playAgainBtn.setVisibility(View.VISIBLE);

        }
    };


    public void playAgainClick(View view)
    {
        playAgainBtn.setVisibility(View.INVISIBLE);
        cnt.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainBtn = (Button)findViewById(R.id.playAgainBTN);
        playAgainBtn.setVisibility(View.INVISIBLE);

        timeTV = (TextView)findViewById(R.id.timeTV);

        cnt.start();
    }
}
