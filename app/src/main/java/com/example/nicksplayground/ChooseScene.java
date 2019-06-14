package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ChooseScene extends AppCompatActivity {
    ListView lvScene;
    ArrayList<String> alSceneList;
    ArrayAdapter<String> aaScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scene);
        lvScene = findViewById(R.id.lvScene);

        alSceneList = new ArrayList<>();
        aaScene = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,alSceneList);
        lvScene.setAdapter(aaScene);

        alSceneList.add("Lost in Public");
        alSceneList.add("Lining Up");
        alSceneList.add("Overstimulated by the noise, sight and information");

       lvScene.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String current = alSceneList.get(i);
               Intent i2 = new Intent(ChooseScene.this,PlayScene.class);
               i2.putExtra("scene",current);
               startActivity(i2);
           }
       });






    }
}
