package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


public class QuizActivity extends AppCompatActivity {

    // Declare global widgets
    TextView txt_Welcome;
    TextView txt_Progress;
    ProgressBar progress_Quiz;
    TextView txt_Question;
    Button btn_OptionA;
    Button btn_OptionB;
    Button btn_OptionC;
    Button btn_Submit;
    String txt_name;
    String[] questionsArray;
    String[] A_optionsArray;
    String[] B_optionsArray;
    String[] C_optionsArray;

    // Declare global variables
    int questionNum, score, selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Check for extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txt_name = extras.getString("Name");
        }

        // Get Quiz Questions & Answers
        questionsArray = getResources().getStringArray(R.array.Questions);
        A_optionsArray = getResources().getStringArray(R.array.A_Options);
        B_optionsArray = getResources().getStringArray(R.array.B_Options);
        C_optionsArray = getResources().getStringArray(R.array.C_Options);

        // Allocate widgets
        txt_Welcome = findViewById(R.id.textWelcome);
        txt_Progress = findViewById(R.id.textProgress);
        progress_Quiz = findViewById(R.id.progressQuiz);
        txt_Question = findViewById(R.id.textQuestion);
        btn_OptionA = findViewById(R.id.buttonOptionA);
        btn_OptionB = findViewById(R.id.buttonOptionB);
        btn_OptionC = findViewById(R.id.buttonOptionC);
        btn_Submit = findViewById(R.id.buttonSubmit);

        // Set quiz variables
        questionNum = 0;
        progress_Quiz.setMax(questionsArray.length - 1);
        progress_Quiz.setProgress(questionNum);
        score = 0;

        // Setup Welcome text
        txt_Welcome.setText(String.format("Welcome %s!", txt_name));

        // Setup for first question
        nextQuestion();
    }

    protected void nextQuestion() {

        // Setup screen texts
        txt_Progress.setText(String.format("%s / %s", questionNum + 1, questionsArray.length));
        progress_Quiz.setProgress(questionNum);
        txt_Question.setText(questionsArray[questionNum]);
        btn_OptionA.setText(A_optionsArray[questionNum]);
        btn_OptionB.setText(B_optionsArray[questionNum]);
        btn_OptionC.setText(C_optionsArray[questionNum]);
        btn_Submit.setText(getResources().getString(R.string.button_submit));

        // Reset button colors
        btn_OptionA.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
        btn_OptionB.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
        btn_OptionC.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));

        // Listeners
        btn_OptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 1; // Set current selection as Option A
                // Color buttons as per selection
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.selected, getTheme()));
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
            }
        });

        btn_OptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 2; // Set current selection as Option B
                // Color buttons as per selection
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.selected, getTheme()));
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
            }
        });

        btn_OptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = 3; // Set current selection as Option C
                // Color buttons as per selection
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.notSelected, getTheme()));
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.selected, getTheme()));
            }
        });

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection > 0) {
                    checkAnswer();
                }
            }
        });
    }

    protected void checkAnswer() {
        // Get Answer list
        int[] answersArray = getResources().getIntArray(R.array.Answers);
        // Find answer to current Q
        int answer = answersArray[questionNum];

        // Adjust score and colour buttons as per selection
        switch (answer) {
            case 1:
                btn_OptionA.setBackgroundColor(getResources().getColor(R.color.correct, getTheme()));
                if (selection == 1) {score++;}
                if (selection == 2) {btn_OptionB.setBackgroundColor(getResources().getColor(R.color.incorrect, getTheme()));}
                if (selection == 3) {btn_OptionC.setBackgroundColor(getResources().getColor(R.color.incorrect, getTheme()));}
            break;
            case 2:
                btn_OptionB.setBackgroundColor(getResources().getColor(R.color.correct, getTheme()));
                if (selection == 1) {btn_OptionA.setBackgroundColor(getResources().getColor(R.color.incorrect, getTheme()));}
                if (selection == 2) {score++;}
                if (selection == 3) {btn_OptionC.setBackgroundColor(getResources().getColor(R.color.incorrect, getTheme()));}
            break;
            case 3:
                btn_OptionC.setBackgroundColor(getResources().getColor(R.color.correct, getTheme()));
                if (selection == 1) {btn_OptionA.setBackgroundColor(getResources().getColor(R.color.incorrect, getTheme()));}
                if (selection == 2) {btn_OptionB.setBackgroundColor(getResources().getColor(R.color.incorrect, getTheme()));}
                if (selection == 3) {score++;}
            break;
            default:
            break;
        }
        // Remove Option Button Listeners
        btn_OptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        btn_OptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        btn_OptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        // Change Submit to either Next Question or Finish Quiz
        if (questionNum < questionsArray.length - 1) {
            btn_Submit.setText(getResources().getString(R.string.button_next));
            btn_Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    questionNum++;
                    selection = 0;
                    nextQuestion();
                }
            });
        } else {
            btn_Submit.setText(getResources().getString(R.string.button_end));
            btn_Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(QuizActivity.this, FinishActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("Name",txt_name);
                    extras.putInt("Score",score);
                    extras.putInt("QuizLength",questionsArray.length);
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
}