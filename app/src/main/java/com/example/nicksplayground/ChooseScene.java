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


public class ChooseScene extends AppCompatActivity {
    ListView lvScene;
    ArrayList<Scene> alSceneList;
    ArrayAdapter<Scene> aaScene;
    AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scene);
        lvScene = findViewById(R.id.lvScene);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        alSceneList = new ArrayList<Scene>();
        final SceneAdapter aaScene = new SceneAdapter(this, R.layout.scene_row,alSceneList);
        client.get("https://nicksplaygroundfyp2019.000webhostapp.com/getScenario.php", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){

                try{
                    for (int i = 0; i < response.length(); i++){
                        JSONObject jsonObj = response.getJSONObject(i);
                        int id = jsonObj.getInt("scene_id");
                        String name = jsonObj.getString("cat_name");
                        String img = jsonObj.getString("media");
                        Scene scene = new Scene(id,img,name);
                        alSceneList.add(scene);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

                lvScene.setAdapter(aaScene);

                lvScene.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent i2 = new Intent(ChooseScene.this,PlayScene.class);
                        i2.putExtra("scene",i);
                        startActivity(i2);
                    }
                });

            }//end onSuccess
        });
    }


}
