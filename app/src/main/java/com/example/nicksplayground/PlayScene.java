package com.example.nicksplayground;

import android.content.Intent;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;

public class PlayScene extends AppCompatActivity {

    private static final String TAG = "";
    private VideoView vw;
    private MediaController mediaController;
    TextView tvSubtitles;
    Button btnQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_scene);
        vw = findViewById(R.id.vw);
        btnQuiz = findViewById(R.id.btnSkip);
        tvSubtitles = findViewById(R.id.textViewSubs);

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayScene.this,QuizPage.class);
                startActivity(intent);
            }
        });

        Intent getIntent = getIntent();
        String scene = getIntent.getStringExtra("scene");

        String fullScreen =  getIntent().getStringExtra("fullScreenInd");
        if("y".equals(fullScreen)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
        if(isLandScape()){
            mediaController = new FullScreenMediaMediaController(this);
        }else {
            mediaController = new MediaController(this);
        }

        vw.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent i = new Intent(PlayScene.this, QuizPage.class);
                startActivity(i);
            }
        });

        if(scene.equalsIgnoreCase("0")){
            String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.market;
            Uri uri = Uri.parse(uriPath);
            vw.setVideoURI(uri);
            vw.requestFocus();
            mediaController.setAnchorView(vw);
            vw.addSubtitleSource(getResources().openRawResource(R.raw.en), MediaFormat.createSubtitleFormat("text/vtt", Locale.ENGLISH.getLanguage()));
            vw.setMediaController(mediaController);
            vw.start();

        }else if(scene.equalsIgnoreCase("1")){
            String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.fastfood;
            Uri uri = Uri.parse(uriPath);
            vw.setVideoURI(uri);
            vw.requestFocus();
            mediaController.setAnchorView(vw);
            vw.setMediaController(mediaController);
            vw.start();
        }else{
            String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.mrt;
            Uri uri = Uri.parse(uriPath);
            vw.setVideoURI(uri);
            vw.requestFocus();
            mediaController.setAnchorView(vw);
            vw.setMediaController(mediaController);
            vw.start();
        }



    }

    private boolean isLandScape(){
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay();
        int rotation = display.getRotation();

        if (rotation == Surface.ROTATION_90
                || rotation == Surface.ROTATION_270) {
            return true;
        }
        return false;
    }
}
