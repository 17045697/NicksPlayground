package com.example.nicksplayground;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class SubmitQuizReport extends AppCompatActivity {
    TextView tvReport;
    Button btnSend;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quiz_report);
        btnSend = findViewById(R.id.btnSend);
        client = new AsyncHttpClient();
        tvReport = findViewById(R.id.tvReport);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String student = prefs.getString("student_name", "");
        final String student_id = prefs.getString("s_id","");
        Log.i("student_id",student_id);
        String classname = prefs.getString("class","");
        String timing = prefs.getString("timing","");
        final String quizQns = prefs.getString("question","");
        final String quizID = prefs.getString("quizID","");
        final String questionID = prefs.getString("questionID", "");
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        final String formattedDate = df.format(c);

        String message = "Student Name: " + student + "\n" + "Date: " + formattedDate + "\n" + "Class Name: " + classname + "\n" + "Timing: " + timing + "\n" + "Quiz ID: " + quizID + "\n" + "Quiz Question: " + quizQns;
        tvReport.setText(message);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("quizID", quizID);
                params.add("studentID",student_id);
                params.add("questionID",questionID);
                params.add("date",formattedDate);
                client.post("https://nicksplaygroundfyp2019.000webhostapp.com/uploadQuizResults.php", params, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            boolean status = response.getBoolean("inserted");
                            if (status) {
                                Intent intent = new Intent(SubmitQuizReport.this, ChooseEnviroment.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getBaseContext(), "Insert failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}


