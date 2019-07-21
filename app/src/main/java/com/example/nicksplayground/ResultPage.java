package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ResultPage extends AppCompatActivity {

    ListView lvStudent;
    ArrayList<String> alStudentList;
    ArrayAdapter<String> aaStudent;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        lvStudent = findViewById(R.id.lvStudent);
        client = new AsyncHttpClient();

        alStudentList = new ArrayList<>();
        aaStudent = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,alStudentList);

        client.get("https://nicksplaygroundfyp2019.000webhostapp.com/getStudents.php",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){

                try{
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObj = response.getJSONObject(i);
                        String name = jsonObj.getString("student");
                        String place = jsonObj.getString("class");
                        alStudentList.add(name);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                lvStudent.setAdapter(aaStudent);

                lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent i2 = new Intent(ResultPage.this,SubmitQuizReport.class);
                        Object o = adapterView.getItemAtPosition(i);
                        String name = o.toString();
                        i2.putExtra("student",name);
                        Log.d("tess", name);
                        startActivity(i2);
                    }
                });

            }//end onSuccess
        });
    }


}
