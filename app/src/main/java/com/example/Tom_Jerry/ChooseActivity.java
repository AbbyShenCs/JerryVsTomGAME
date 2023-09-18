package com.example.Tom_Jerry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnhj1,btnhj2,btnhj3;
    private SoundPool soundPool;
    private int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        getSupportActionBar().hide();//隐藏标题栏
        initSound();
        init();

    }
    private void init() {

        btnhj1=findViewById(R.id.btn_hj1);
        btnhj2=findViewById(R.id.btn_hj2);
        btnhj3=findViewById(R.id.btn_hj3);

        btnhj1.setOnClickListener(this);
        btnhj2.setOnClickListener(this);
        btnhj3.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hj1:
                my.choose=1;
                playSound();
                Intent intent=new Intent(ChooseActivity.this, GameActivity.class);
                startActivity(intent);
                ChooseActivity.this.finish();
                break;
            case R.id.btn_hj2:
                my.choose=2;
                playSound();
                Intent intent2=new Intent(ChooseActivity.this, GameActivity.class);
                startActivity(intent2);
                ChooseActivity.this.finish();
                break;
            case R.id.btn_hj3:
                my.choose=3;
                playSound();
                Intent intent3=new Intent(ChooseActivity.this, GameActivity.class);
                startActivity(intent3);
                ChooseActivity.this.finish();
                break;
            default:
                break;
        }
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
