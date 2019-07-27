package com.example.nicksplayground;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

public class QuizPage extends AppCompatActivity {

    TextView tvQns, tvResult;
    private AsyncHttpClient client;
    private MediaPlayer mediaPlayer;
    Button btnQns1,btnQns2;
    int result = 0;
    int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        tvQns = findViewById(R.id.tvQuestion);
        tvResult = findViewById(R.id.tvResult);
        btnQns1 = findViewById(R.id.btnQns1);
        btnQns2 = findViewById(R.id.btnQns2);

        Intent intent = getIntent();
        int get = intent.getIntExtra("id", -1);
        final int id = get + 1;
        client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("id",id +"");
        client.post("https://nicksplaygroundfyp2019.000webhostapp.com/getQuizAndroid.php", params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    Log.d("testing", id+"");
                    JSONObject jsonObj = (JSONObject) response.get(0);
                    String qns_id = jsonObj.getString("question_id");
                    String qns = jsonObj.getString("question");
                    String ans1 = jsonObj.getString("answer_1");
                    String ans2 = jsonObj.getString("answer_2");
                    final String ans = jsonObj.getString("correct_answer");
                    tvQns.setText(qns);
                    btnQns1.setText(ans1);
                    btnQns2.setText(ans2);

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(QuizPage.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("quizID", String.valueOf(id));
                    editor.putString("questionID", qns_id);
                    editor.putString("question", qns);
                    editor.commit();

                    btnQns1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String num = "1";
                            if (num.equalsIgnoreCase(ans)){
                                mediaPlayer = (MediaPlayer) MediaPlayer.create(getApplicationContext(),R.raw.applause);
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.start();
                                result += 1;
                                tvResult.setText("");
                                Log.i("quiz", String.valueOf(result));

                                SharedPreferences prefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(QuizPage.this);
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
                                                Intent intent = new Intent(QuizPage.this, ChooseEnviroment.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getBaseContext(), "Insert failed", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                Intent intent = new Intent(QuizPage.this,ChooseEnviroment.class);
                                startActivity(intent);
                            }else{
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.start();
                                tries += 1;
                                tvResult.setText("Try Again");

                            }
                        }
                    });

                    btnQns2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String num = "2";
                            if (num.equalsIgnoreCase(ans)){
                                mediaPlayer = (MediaPlayer) MediaPlayer.create(getApplicationContext(),R.raw.applause);
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.start();
                                result += 1;
                                Log.i("quiz2", String.valueOf(result));
                                tvResult.setText("");

                                SharedPreferences prefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(QuizPage.this);
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
                                                Intent intent = new Intent(QuizPage.this, ChooseEnviroment.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getBaseContext(), "Insert failed", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                Intent intent = new Intent(QuizPage.this,ChooseEnviroment.class);
                                startActivity(intent);
                            }else{
                                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.start();
                                tries += 1;
                                tvResult.setText("Try Again");
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
