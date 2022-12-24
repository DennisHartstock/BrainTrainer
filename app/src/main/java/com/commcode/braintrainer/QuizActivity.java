package com.commcode.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvTimer;
    private Button btStart;
    private TextView tvQuestion;
    private TextView tvOption1;
    private TextView tvOption2;
    private TextView tvOption3;
    private TextView tvOption4;

    private final ArrayList<TextView> answers = new ArrayList<>();
    private int rightAnswer;
    private int rightAnswerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initViews();

        answers.add(tvOption1);
        answers.add(tvOption2);
        answers.add(tvOption3);
        answers.add(tvOption4);

        btStart.setOnClickListener(view -> {
            btStart.setVisibility(View.GONE);
            tvQuestion.setVisibility(View.VISIBLE);
            setTimer(60);
            viewNextQuestion();
        });

        tvOption1.setOnClickListener(view -> viewNextQuestion());
        tvOption2.setOnClickListener(view -> viewNextQuestion());
        tvOption3.setOnClickListener(view -> viewNextQuestion());
        tvOption4.setOnClickListener(view -> viewNextQuestion());
    }

    private void viewNextQuestion() {
        generateQuestion();
        setAnswers();
    }

    private void setAnswers() {
        rightAnswerPosition = new Random().nextInt(4);
        for (int i = 0; i < answers.size(); i++) {
            if (i == rightAnswerPosition) {
                answers.get(i).setText(String.format("%s", rightAnswer));
            } else {
                answers.get(i).setText(String.format("%s", generateAnswers()));
            }
        }
    }

    private int generateAnswers() {
        int answer;
        do {
            answer = new Random().nextInt(20) + 1;
        } while (answer == rightAnswer);
        return answer;
    }

    private void generateQuestion() {
        int a = new Random().nextInt(10) + 1;
        int b = new Random().nextInt(10) + 1;
        String question = String.format("%s + %s = ", a, b);
        tvQuestion.setText(question);
        rightAnswer = a + b;
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