package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;

public class ChooseClass extends AppCompatActivity {

    GridView gv;
    String[] lesson = {"Responsibility","Responsibility","Respect","Respect","Resilience","Resilience"};
    String[] timing = {"AM","PM","AM","PM","AM","PM"};
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        gv = (GridView) findViewById(R.id.gridview);
        client = new AsyncHttpClient();
        ClassAdapter customAdapter = new ClassAdapter(this, lesson,timing);
        gv.setAdapter(customAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(ChooseClass.this, ChooseEnviroment.class);
                intent.putExtra("class", lesson[position]);
                intent.putExtra("time", timing[position]);
                startActivity(intent); // start Intent
            }
        });

    }
}