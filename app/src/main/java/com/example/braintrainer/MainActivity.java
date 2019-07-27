package com.example.braintrainer;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewQuestin;
    private TextView textViewTimer;
    private TextView textViewScore;
    private TextView textViewOp0;
    private TextView textViewOp1;
    private TextView textViewOp2;
    private TextView textViewOp3;

    private ArrayList<TextView> options = new ArrayList<>();

    private String question;
    private int rightAnswer;
    private int rightAnswerPosition;
    private boolean isPositive;
    private int min = 5;
    private int max = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuestin = findViewById(R.id.textViewQuestin);
        textViewTimer = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);
        textViewOp0 = findViewById(R.id.textViewOp0);
        textViewOp1 = findViewById(R.id.textViewOp1);
        textViewOp2 = findViewById(R.id.textViewOp2);
        textViewOp3 = findViewById(R.id.textViewOp3);

        options.add(textViewOp0);
        options.add(textViewOp1);
        options.add(textViewOp2);
        options.add(textViewOp3);

        playNext();

    }

    private void playNext(){

        generateQuestin();
        for (int i = 0; i < options.size(); i++) {
            if (i == rightAnswerPosition){
                options.get(i).setText(Integer.toString(rightAnswer));
            }else {
                options.get(i).setText(Integer.toString(generateWrongAnswer()));
            }
        }
    }

    private void generateQuestin(){

        int a = (int)(Math.random() * (max - min + 1) + min);
        int b = (int)(Math.random() * (max - min + 1) + min);
        int mark = (int)(Math.random() * 2);
        isPositive = mark == 1;

        if (isPositive){
            rightAnswer = a + b;
            question = String.format("%s + %s", a, b);
        }else {
            rightAnswer = a - b;
            question = String.format("%s - %s", a, b);
        }
        textViewQuestin.setText(question);
        rightAnswerPosition = (int)(Math.random() * 4);
    }

    private int generateWrongAnswer(){

        int result;

        do {
            result = (int)(Math.random() * max * 2 + 1) - (max - min);
        }while (result == rightAnswer);

        return result;
    }

    public void onClickAnswer(View view) {

        playNext();
    }
}
