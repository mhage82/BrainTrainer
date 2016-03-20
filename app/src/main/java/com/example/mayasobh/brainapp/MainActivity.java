package com.example.mayasobh.brainapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout mainlayout;
    RelativeLayout golayout;
    GridLayout choiceLayout;
    TextView timeTV;
    TextView resultTV;
    TextView answerTV;
    TextView choice1;
    TextView choice2;
    TextView choice3;
    TextView choice4;
    TextView percentTV;

    Button playAgainBtn;

    Random randgen = new Random();

    int totalAnswers = 0;
    int correctAnswers = 0;

    int number1 = 0;
    int number2 = 0;
    int answer = 0;
    int ans=0;
    boolean answerTrue= false;

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
            enableLayout(choiceLayout, false);

            float score = ((float)correctAnswers/totalAnswers)*100;
            percentTV.setVisibility(View.VISIBLE);
            percentTV.setText("Your score is : "+Integer.toString((int)score) +" %");
        }
    };


    public void enableLayout(ViewGroup layout, boolean enable)
    {
        layout.setEnabled(enable);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                enableLayout((ViewGroup) child, enable);
            } else {
                child.setEnabled(enable);
            }
        }
    }

    public void setResultsTV()
    {
        resultTV.setText(Integer.toString(correctAnswers )+ "/" + Integer.toString(totalAnswers));
    }

    public void setAnswerTV()
    {
        answerTV.setText(Integer.toString(number1)+
                        "  +  " +
                        Integer.toString(number2));
    }
    public void goClick(View view)
    {
        golayout.setVisibility(View.INVISIBLE);
        mainlayout.setVisibility(View.VISIBLE);
        cnt.start();
    }
    public void playAgainClick(View view)
    {
        playAgainBtn.setVisibility(View.INVISIBLE);
        enableLayout(choiceLayout, true);
        correctAnswers = 0;
        totalAnswers = 0;
        generateNewQ();
        setAnswerTV();
        cnt.start();
        percentTV.setVisibility(View.INVISIBLE);

    }

    public void answerClick(View view)
    {
        totalAnswers++;

        int answer = Integer.parseInt(view.getTag().toString());

        if(answer == ans)
        {
            answerTrue = true;
            correctAnswers++;
        }
        else
        {
            answerTrue = false;
        }

        setResultsTV();

        generateNewQ();
        setAnswerTV();
    }
    public void generateNewQ()
    {
        number1 = randgen.nextInt(50)+1;
        number2 = randgen.nextInt(50)+1;
        answer = number1 + number2;
        ans = randgen.nextInt(4);

        switch (ans)
        {
            case 0:
                choice1.setText(Integer.toString(answer));
                choice2.setText(Integer.toString(randgen.nextInt(100)+1));
                choice3.setText(Integer.toString(randgen.nextInt(100)+1));
                choice4.setText(Integer.toString(randgen.nextInt(100)+1));
                break;
            case 1:
                choice2.setText(Integer.toString(answer));
                choice1.setText(Integer.toString(randgen.nextInt(100)+1));
                choice3.setText(Integer.toString(randgen.nextInt(100)+1));
                choice4.setText(Integer.toString(randgen.nextInt(100)+1));
                break;
            case 2:
                choice3.setText(Integer.toString(answer));
                choice2.setText(Integer.toString(randgen.nextInt(100)+1));
                choice1.setText(Integer.toString(randgen.nextInt(100)+1));
                choice4.setText(Integer.toString(randgen.nextInt(100)+1));
                break;
            case 3:
                choice4.setText(Integer.toString(answer));
                choice2.setText(Integer.toString(randgen.nextInt(100)+1));
                choice3.setText(Integer.toString(randgen.nextInt(100)+1));
                choice1.setText(Integer.toString(randgen.nextInt(100)+1));
                break;
            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTV = (TextView)findViewById(R.id.timeTV);
        resultTV = (TextView)findViewById(R.id.scoreTV);
        answerTV = (TextView)findViewById(R.id.answerTV);
        choice1 = (TextView)findViewById(R.id.randAns1TV);
        choice2 = (TextView)findViewById(R.id.randAns2TV);
        choice3 = (TextView)findViewById(R.id.randAns3TV);
        choice4 = (TextView)findViewById(R.id.randAns4TV);
        percentTV = (TextView)findViewById(R.id.percentTV);

        mainlayout = (RelativeLayout)findViewById(R.id.MainLayout);
        mainlayout.setVisibility(View.INVISIBLE);

        golayout = (RelativeLayout)findViewById(R.id.goLayout);

        choiceLayout = (GridLayout)findViewById(R.id.choiceLayout);
        playAgainBtn = (Button)findViewById(R.id.playAgainBTN);
        playAgainBtn.setVisibility(View.INVISIBLE);

        generateNewQ();
        setResultsTV();
        setAnswerTV();

    }
}
