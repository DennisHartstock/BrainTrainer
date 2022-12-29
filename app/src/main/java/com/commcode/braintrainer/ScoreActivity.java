package com.commcode.braintrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvResult = findViewById(R.id.tvResult);
        btNewGame = findViewById(R.id.btNewGame);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("result")) {
            int result = intent.getIntExtra("result", 0);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int score = preferences.getInt("score", 0);
            tvResult.setText(String.format("Score: %s\nYour result: %s", score, result));
        }

        btNewGame.setOnClickListener(view -> startActivity(QuizActivity.newIntent(view.getContext())));
    }
}