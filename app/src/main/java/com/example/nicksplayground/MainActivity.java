package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUser;
    EditText etPass;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();

                if (user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
                    Intent i = new Intent(MainActivity.this,ChooseEnviroment.class);
                    startActivity(i);
                }else if(user.equalsIgnoreCase("teacher") && pass.equalsIgnoreCase("teacher")){
                    Intent i = new Intent(MainActivity.this,ChooseEnviroment.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed, Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
