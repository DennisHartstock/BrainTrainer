package com.commcode.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvTimer;
    private Button btStart;
    private TextView tvQuestion;
    private TextView tvOption1;
    private TextView tvOption2;
    private TextView tvOption3;
    private TextView tvOption4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initViews();

        btStart.setOnClickListener(view -> {
            btStart.setVisibility(View.GONE);
            tvQuestion.setVisibility(View.VISIBLE);
            setTimer(60);
        });


        int a = 0;
        int b = 0;
        String question = String.format("%s + %s = ", a, b);
        tvQuestion.setText(question);
    }

    public void setTimer(int seconds) {
        CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000L, 1_000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(String.format("%s", (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                Toast.makeText(QuizActivity.this, "Time is over", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void initViews() {
        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        btStart = findViewById(R.id.btStart);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
        tvOption3 = findViewById(R.id.tvOption3);
        tvOption4 = findViewById(R.id.tvOption4);
    }
}