package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


public class QuizActivity extends AppCompatActivity {

    String txt_name;
    String[] questionsArray;
    String[] A_optionsArray;
    String[] B_optionsArray;
    String[] C_optionsArray;
    int[] answersArray;
    int questionNum, selection, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txt_name = extras.getString("Name");
        }

        // get Quiz Resources
        questionsArray = getResources().getStringArray(R.array.Questions);
        A_optionsArray = getResources().getStringArray(R.array.A_Options);
        B_optionsArray = getResources().getStringArray(R.array.B_Options);
        C_optionsArray = getResources().getStringArray(R.array.C_Options);
        answersArray = getResources().getIntArray(R.array.Answers);

        // Allocate widgets
        TextView txt_Welcome=findViewById(R.id.textWelcome);
        TextView txt_Progress=findViewById(R.id.textProgress);
        ProgressBar progress_Quiz=findViewById(R.id.progressQuiz);
        TextView txt_Question=findViewById(R.id.textQuestion);
        Button btn_OptionA=findViewById(R.id.buttonOptionA);
        Button btn_OptionB=findViewById(R.id.buttonOptionB);
        Button btn_OptionC=findViewById(R.id.buttonOptionC);
        Button btn_Submit=findViewById(R.id.buttonSubmit);

        // Set quiz variables
        questionNum = 0;
        score = 0;

        // set screen texts

        txt_Welcome.setText(String.format("Welcome %s!", txt_name));
        txt_Progress.setText(String.format("%s / %s", questionNum + 1, questionsArray.length));
        txt_Progress.setText(String.format("%s / %s", questionNum + 1, questionsArray.length));
        progress_Quiz.setProgress(0);
        txt_Question.setText(questionsArray[questionNum]);
        btn_OptionA.setText(A_optionsArray[questionNum]);
        btn_OptionB.setText(B_optionsArray[questionNum]);
        btn_OptionC.setText(C_optionsArray[questionNum]);


        // Listeners
        btn_OptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 1;
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.selected, getTheme()));
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
            }
        });

        btn_OptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 2;
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.selected, getTheme()));
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
            }
        });

        btn_OptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 3;
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.selected, getTheme()));
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}