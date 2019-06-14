package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseEnviroment extends AppCompatActivity {

    ImageButton ibSuper;
    ImageButton ibFast;
    ImageButton ibMrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enviroment);

        ibSuper = findViewById(R.id.ibSuper);
        ibFast = findViewById(R.id.ibFast);
        ibMrt = findViewById(R.id.ibMrt);

        ibSuper.setImageResource(R.drawable.supermarket);
        ibFast.setImageResource(R.drawable.fastfood);
        ibMrt.setImageResource(R.drawable.mrt);


        ibSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 1;
                Intent intent = new Intent(ChooseEnviroment.this,ChooseScene.class);
                intent.putExtra("id", i);
                startActivity(intent);

            }
        });

        ibFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = 2;
                Intent intent = new Intent(ChooseEnviroment.this,ChooseScene.class);
                intent.putExtra("id", i);
                startActivity(intent);

            }
        });

        ibMrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 3;
                Intent intent = new Intent(ChooseEnviroment.this,ChooseScene.class);
                intent.putExtra("id", i);
                startActivity(intent);
            }
        });
    }
}
