package com.example.braintrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("counterOfRightAnswers")){
            int result = intent.getIntExtra("counterOfRightAnswers", 0);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int max = preferences.getInt("max", 0);

            String score = String.format("Your result: %s\nMax result %s", result, max);
            textViewResult.setText(score);
        }
    }

    public void onClickRestart(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
