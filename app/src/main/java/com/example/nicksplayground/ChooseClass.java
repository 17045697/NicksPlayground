package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class ChooseClass extends AppCompatActivity {

    GridView gv;
    String[] lesson = {"A","B","C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        gv = (GridView) findViewById(R.id.gridview);
        ClassAdapter customAdapter = new ClassAdapter(this, lesson);
        gv.setAdapter(customAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(ChooseClass.this, ChooseEnviroment.class);
                intent.putExtra("class", lesson[position]); // put image data in Intent
                startActivity(intent); // start Intent
            }
        });
    }
}