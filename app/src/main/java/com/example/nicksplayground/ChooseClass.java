package com.example.nicksplayground;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ChooseClass extends AppCompatActivity {

    GridView gv;
    String[] lesson = {"Responsibility AM","Responsibility PM","Respect AM","Respect PM","Resilience AM","Resilience PM"};
    //String[] timing = {"AM","PM","AM","PM","AM","PM"};
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        gv = (GridView) findViewById(R.id.gridview);
        client = new AsyncHttpClient();



        ClassAdapter customAdapter = new ClassAdapter(this, lesson);
        gv.setAdapter(customAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(ChooseClass.this, ResultPage.class);
                //intent.putExtra("class", lesson[position]);
                //intent.putExtra("time", timing[position]);
                startActivity(intent); // start Intent

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChooseClass.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("class", lesson[position]);
                //editor.putString("timing", timing[position]);
                editor.commit();
            }
        });

    }
}