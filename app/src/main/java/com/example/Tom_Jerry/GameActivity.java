package com.example.Tom_Jerry;


import static com.example.Tom_Jerry.MusicServer.play;
import static com.example.Tom_Jerry.MusicServer.stop;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private long time;//用于检测按两次 "再按一次退出游戏"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        hua game = new hua(this);
        setContentView(game);

        //setContentView()不过这里只能添加一个控件，默认铺满屏幕
        //2、设置此页面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event) { //返回键
        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getRepeatCount() == 0){
            long t=System.currentTimeMillis();//获取系统时间
            if(t-time<=1000){
                exit(); //如果500毫秒内按下两次返回键则退出游戏
            }else{
                time=t;
                Toast.makeText(getApplicationContext(),"再按一次退出游戏",Toast.LENGTH_SHORT).show();
            }


            return true;
        }
        return false;

    }



    public void exit(){
        GameActivity.this.finish();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                System.exit(0);
            }
        }).start();
    }

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
        switch (my.bgmchoose) {

            case 1: play(this, R.raw.bgmusic);break;
            case 2: play(this, R.raw.bgmusic1);break;
            case 3: play(this, R.raw.bgmusic2);break;
            case 4: play(this, R.raw.bgmusic3);break;

        }
    }




}

