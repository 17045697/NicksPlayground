package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText etUser;
    EditText etPass;
    Button btnLogin;
    private AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String user = etUser.getText().toString();
//                String pass = etPass.getText().toString();
//
//
//                if (user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")) {
//                    Intent i = new Intent(MainActivity.this, ChooseEnviroment.class);
//                    startActivity(i);
//                } else if (user.equalsIgnoreCase("teacher") && pass.equalsIgnoreCase("teacher")) {
//                    Intent i = new Intent(MainActivity.this, ChooseEnviroment.class);
//                    startActivity(i);
//                } else {
//                    Toast.makeText(MainActivity.this, "Login Failed, Try Again", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUser.length() == 0 || etPass.length() == 0) {
                    Toast.makeText(getBaseContext(), "Please key in credentials", Toast.LENGTH_LONG).show();
                } else {
                    client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("username", etUser.getText().toString());
                    params.add("password", etPass.getText().toString());
                    client.post("https://nicksplaygroundfyp2019.000webhostapp.com/doLoginAndroid.php", params, new JsonHttpResponseHandler() {
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                boolean status = response.getBoolean("authenticated");
                                if (status) {
                                    int id = response.getInt("user_id");
                                    Intent intent = new Intent(getBaseContext(), ChooseEnviroment.class);
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }


        }

