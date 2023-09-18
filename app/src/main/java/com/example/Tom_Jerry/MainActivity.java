package com.example.Tom_Jerry;


import static com.example.Tom_Jerry.MusicServer.play;
import static com.example.Tom_Jerry.MusicServer.stop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private   Button startbtn,settingbtn,exitbtn;
    private SoundPool soundPool;
    private int soundID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏标题栏
        initSound();
        //setContentView(new hua(this));
        //setContentView()跟swing的add()差不多吧，不过这里只能添加一个控件，默认铺满屏幕
        //2、设置此页面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3、写登录界面的业务逻辑
        init();
    }

    private void init() {

        startbtn=findViewById(R.id.start_btn);
        settingbtn=findViewById(R.id.setting_btn);
        exitbtn=findViewById(R.id.exit_btn);

        startbtn.setOnClickListener(this);
        settingbtn.setOnClickListener(this);
        exitbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                playSound();
                Intent intent=new Intent(MainActivity.this, NanduActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            case R.id.setting_btn:
                playSound();
                Intent intent_Setting=new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent_Setting);
                break;
            case R.id.exit_btn:
                playSound();
                System.exit(0);
                MainActivity.this.finish();
                break;
            default:
                break;
        }
    }




 /*         start_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);

            my.ispause=true;
            MainActivity.this.finish();
        }
    });

        setting_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MusicActivity.class);
            startActivity(intent);

        }
    });*/






    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        stop(this);

    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        play(this, R.raw.bgm1);
    }


    @SuppressLint("NewApi")
    private void initSound() {
        soundPool = new SoundPool.Builder().build();
        soundID = soundPool.load(this, R.raw.button, 1);
    }


    private void playSound() {
        soundPool.play(
                soundID,
                1f,      //左耳道音量【0~1】
                1f,      //右耳道音量【0~1】
                2,         //播放优先级【0表示最低优先级】
                0,         //循环模式【0表示循环一次，-1表示一直循环，其他表示数字+1表示当前数字对应的循环次数】
                1          //播放速度【1是正常，范围从0~2】
        );
    }










}
