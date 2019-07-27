package com.example.nicksplayground;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    ArrayList<Student> alStudentList;
    ArrayAdapter<Student> aaStudent;
    AsyncHttpClient client;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        lvStudent = findViewById(R.id.lvStudent);
        client = new AsyncHttpClient();

        alStudentList = new ArrayList<>();
        final StudentAdapter aaStudent = new StudentAdapter(this,R.layout.student_row,alStudentList);

        client.get("https://nicksplaygroundfyp2019.000webhostapp.com/getStudents.php",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){

                try{
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObj = response.getJSONObject(i);
                        id = jsonObj.getString("student_id");
                        String name = jsonObj.getString("student");
                        int sid = Integer.parseInt(id);
                        String place = jsonObj.getString("class");
                        Student student = new Student(sid,name);
                        alStudentList.add(student);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                lvStudent.setAdapter(aaStudent);

                lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Student selectedstudent = alStudentList.get(i);
                        Intent i2 = new Intent(ResultPage.this,ChooseEnviroment.class);
                        String id = String.valueOf(selectedstudent.getId());
                        String name = selectedstudent.getName();
                        startActivity(i2);

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ResultPage.this);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("student_name", name);
                        editor.putString("s_id", id);
                        editor.commit();
                    }
                });

            }//end onSuccess
        });
    }


}
