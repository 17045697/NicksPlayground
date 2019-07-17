package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class QuizPage extends AppCompatActivity {

    TextView tvQns, tvResult, tvAns1, tvAns2;
    private AsyncHttpClient client;
    int result = 0;
    int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        tvQns = findViewById(R.id.tvQuestion);
        tvAns1 = findViewById(R.id.tvQns1);
        tvAns2 = findViewById(R.id.tvQns2);
        tvResult = findViewById(R.id.tvResult);
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
                    Log.i("testing", ans);
                    tvQns.setText(qns);
                    tvAns1.setText(ans1);
                    tvAns2.setText(ans2);

                    tvAns1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String num = "1";
                            if (num.equalsIgnoreCase(ans)){
                                result += 1;
                                tvResult.setText("");
                                Log.i("quiz", String.valueOf(result));
                            }else{
                                tries += 1;
                                tvResult.setText("Try Again");

                            }
                        }
                    });

                    tvAns2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String num = "2";
                            if (num.equalsIgnoreCase(ans)){
                                result += 1;
                                Log.i("quiz2", String.valueOf(result));
                                tvResult.setText("");
                            }else{
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
