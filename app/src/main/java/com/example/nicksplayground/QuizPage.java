package com.example.nicksplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity {

    TextView tvQns, tvAns1, tvAns2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        tvQns = findViewById(R.id.textView4);
        tvAns1 = findViewById(R.id.textView5);
        tvAns2 = findViewById(R.id.textView6);
        tvQns.setText("What should you do if you are lost?");
        tvAns1.setText("Do Something");
        tvAns2.setText("Do Something 2");

    }
}
