package com.example.Tom_Jerry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NanduActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_easy,btn_normal,btn_hard,btn_impossible;
    private SoundPool soundPool;
    private int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nandu);
        getSupportActionBar().hide();//隐藏标题栏
        initSound();
        init();

    }
    private void init() {

        btn_easy=findViewById(R.id.btn_easy);
        btn_normal=findViewById(R.id.btn_normal);
        btn_hard=findViewById(R.id.btn_hard);
        btn_impossible=findViewById(R.id.btn_impossible);

        btn_easy.setOnClickListener(this);
        btn_normal.setOnClickListener(this);
        btn_hard.setOnClickListener(this);
        btn_impossible.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_easy:
                my.nandu=1;
                playSound();
                Intent intent=new Intent(NanduActivity.this, ChooseActivity.class);
                startActivity(intent);
                NanduActivity.this.finish();
                break;
            case R.id.btn_normal:
                my.nandu=2;
                playSound();
                Intent intent2=new Intent(NanduActivity.this, ChooseActivity.class);
                startActivity(intent2);
                NanduActivity.this.finish();
                break;
            case R.id.btn_hard:
                my.nandu=3;
                playSound();
                Intent intent3=new Intent(NanduActivity.this, ChooseActivity.class);
                startActivity(intent3);
                NanduActivity.this.finish();
                break;
            case R.id.btn_impossible:
                my.nandu=4;
                playSound();
                Intent intent4=new Intent(NanduActivity.this, ChooseActivity.class);
                startActivity(intent4);
                NanduActivity.this.finish();
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
