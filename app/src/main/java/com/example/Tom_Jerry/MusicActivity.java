package com.example.Tom_Jerry;


import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MusicActivity extends AppCompatActivity {
    private int soundID;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    //音乐播放器
    private SoundPool soundPool,soundPool2;
    //使用一个集合存储音乐
    private Map<Integer,Integer> sounds;
    int playStram=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        getSupportActionBar().hide();//隐藏标题栏
        //创建一个播放器
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,0);
        initSound();
        //把需要播放的音乐加载到播放器中
        sounds = new HashMap<Integer, Integer>();
        sounds.put(1,soundPool.load(this,R.raw.bgm1,1));
        sounds.put(2,soundPool.load(this,R.raw.bgmusic,1));
        sounds.put(3,soundPool.load(this,R.raw.bgmusic1,1));
        sounds.put(4,soundPool.load(this,R.raw.bgmusic2,1));
        sounds.put(5,soundPool.load(this,R.raw.bgmusic3,1));
   /*   sounds.put(4,soundPool.load(this,R.raw.get_bomb,1));
        sounds.put(5,soundPool.load(this,R.raw.use_bomb,1));*/


        btn1 =findViewById(R.id.btn_1);
        btn2 =findViewById(R.id.btn_2);
        btn3 =findViewById(R.id.btn_3);
        btn4 =findViewById(R.id.btn_4);
        btn5 =findViewById(R.id.btn_5);
        btn6 =findViewById(R.id.btn_6);
        btn7 =findViewById(R.id.btn_7);
        btn8 =findViewById(R.id.btn_8);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                playStram=soundPool.play(sounds.get(2),1,1,2,0,1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                soundPool.play(sounds.get(3),1,1,1,0,1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                soundPool.play(sounds.get(4),1,1,1,0,1);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                soundPool.play(sounds.get(5),1,1,1,0,1);
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                my.bgmchoose=1;
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                my.bgmchoose=2;
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                my.bgmchoose=3;
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                my.bgmchoose=4;
            }
        });



    }

    @SuppressLint("NewApi")
    private void initSound() {
        soundPool2 = new SoundPool.Builder().build();
        soundID = soundPool2.load(this, R.raw.button, 1);
    }


    private void playSound() {
        soundPool2.play(
                soundID,
                0.9f,      //左耳道音量【0~1】
                0.9f,      //右耳道音量【0~1】
                3,         //播放优先级【0表示最低优先级】
                0,         //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
                1          //播放速度【1是正常，范围从0~2】
        );
    }




}
