package com.example.nicksplayground;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                    String qns = jsonObj.getString("question");
                    String ans1 = jsonObj.getString("answer_1");
                    String ans2 = jsonObj.getString("answer_2");
                    final String ans = jsonObj.getString("correct_answer");
                    tvQns.setText(qns);
                    btnQns1.setText(ans1);
                    btnQns2.setText(ans2);

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
                                Intent intent = new Intent(QuizPage.this,ResultPage.class);
                                intent.putExtra("score",result);
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
                                Intent intent = new Intent(QuizPage.this,ResultPage.class);
                                intent.putExtra("score",result);
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
