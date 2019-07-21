package com.example.nicksplayground;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubmitQuizReport extends AppCompatActivity {
    TextView tvReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quiz_report);
        tvReport = findViewById(R.id.tvReport);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Intent getintent = getIntent();
        String student = getintent.getStringExtra("student");
        String classname = prefs.getString("class","");
        String timing = prefs.getString("timing","");
        String quizQns = prefs.getString("question","");
        String quizID = prefs.getString("quizID","");
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        String message = "Student Name: " + student + "\n" + "Date: " + formattedDate + "\n" + "Class Name: " + classname + "\n" + "Timing: " + timing + "\n" + "Quiz ID: " + quizID + "\n" + "Quiz Question: " + quizQns;
        tvReport.setText(message);
    }
}
