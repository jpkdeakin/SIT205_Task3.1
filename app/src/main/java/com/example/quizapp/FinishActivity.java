package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {

    TextView txt_CongratsName;
    TextView txt_Score;
    Button btn_NewQuiz;
    Button btn_Finish;
    String txt_name;
    int score;
    int quizLength;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        txt_name = "";
        score = 0;
        quizLength = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txt_name = extras.getString("Name");
            score = extras.getInt("Score");
            quizLength = extras.getInt("QuizLength");
        }

         txt_CongratsName = findViewById(R.id.textCongratsName);
         txt_Score = findViewById(R.id.textFinalScore);
         btn_NewQuiz = findViewById(R.id.buttonNewQuiz);
         btn_Finish = findViewById(R.id.buttonFinish);

        txt_CongratsName.setText(String.format("%s!",txt_name));
        txt_Score.setText(String.format("%s / %s", score, quizLength));

        btn_NewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                intent.putExtra("Name", txt_name);
                startActivity(intent);
                finish();
            }
        });

        btn_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
