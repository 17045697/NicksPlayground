package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ChooseEnviroment extends AppCompatActivity {

    ListView lvEnvi;
    ArrayList<Environment> alEnvironmentList;
    ArrayAdapter<Environment> aaEnvironment;
    AsyncHttpClient client;
    String envi_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enviroment);
        lvEnvi = findViewById(R.id.lvEnvi);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        alEnvironmentList = new ArrayList<Environment>();
        final EnvironmentAdapter aaEnvironment = new EnvironmentAdapter(this,R.layout.envi_row,alEnvironmentList);
        client.get("https://nicksplaygroundfyp2019.000webhostapp.com/getEnvironment.php", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){

                try{
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObj = response.getJSONObject(i);
                        int id = jsonObj.getInt("e_id");
                        String name = jsonObj.getString("envi_description");
                        String img = jsonObj.getString("image");
                        Environment environment = new Environment(id,img,name);
                        alEnvironmentList.add(environment);
                        envi_id= String.valueOf(id);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                lvEnvi.setAdapter(aaEnvironment);

                lvEnvi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent i2 = new Intent(ChooseEnviroment.this,ChooseScene.class);
                        i2.putExtra("environment",i);
                        i2.putExtra("id",envi_id);
                        startActivity(i2);
                    }
                });

            }//end onSuccess
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_change) {

            // TODO (8) Create an Intent to Create Contact
            // Put the following into intent:- loginId, apikey
            Intent i = new Intent(getBaseContext(), ResultPage.class);
            startActivity(i);

        }else {
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

