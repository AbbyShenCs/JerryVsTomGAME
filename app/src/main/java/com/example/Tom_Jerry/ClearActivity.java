package com.example.Tom_Jerry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ClearActivity extends AppCompatActivity {
    private VideoView videoView;
    private Button btn_start,btn_end;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        getSupportActionBar().hide();//隐藏标题栏
        initView();
        init();
    }

    private void initView() {
        videoView= findViewById(R.id.videoView);
        btn_start=  findViewById(R.id.btn_turnon);
        btn_end=  findViewById(R.id.btn_turnoff);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClearActivity.this, MainActivity.class);
                ClearActivity.this.startActivity(intent);
               System.exit(0);
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               init();
            }
        });
    }

    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        //本地连接地址
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.clear;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }
}
