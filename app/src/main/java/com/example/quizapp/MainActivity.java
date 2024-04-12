package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allocate widgets
        Button ntm_A=findViewById(R.id.buttonStart);
        EditText editText_Name=findViewById(R.id.editTextName);


        // Check for extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String txt_name = extras.getString("Name");
            editText_Name.setText(txt_name);
        }


        ntm_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start Quiz
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("Name", editText_Name.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }
}