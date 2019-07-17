package com.example.nicksplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ChooseEnviroment extends AppCompatActivity {

    ImageButton ibSuper;
    ImageButton ibFast;
    ImageButton ibMrt;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enviroment);

        ibSuper = findViewById(R.id.ibSuper);
        ibFast = findViewById(R.id.ibFast);
        ibMrt = findViewById(R.id.ibMrt);
        client = new AsyncHttpClient();

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


//    @Override
//    protected void onResume() {
//        super.onResume();
//        client.get("https://nicksplaygroundfyp2019.000webhostapp.com/getEnvironment.php", new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response){
//
//                try{
//                    Log.i("magic", response.toString());
//                    for (int i = 0; i < response.length(); i++){
//
//                        JSONObject jsonObj = response.getJSONObject(i);
//                        String description = jsonObj.getString("envi_description");
//
//
//                    }
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//
//
//            }//end onSuccess
//        });
//    }
}
