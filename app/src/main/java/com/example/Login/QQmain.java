package com.example.Login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Tom_Jerry.R;
public class QQmain extends AppCompatActivity {

    private Button btn_newlog,btn_entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置页面布局为我们之前写的登陆界面
        setContentView(R.layout.qq_main);
        //2.设置此界面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3.写登录界面的业务逻辑
        init();
    }


    private void init(){

        btn_entry = findViewById(R.id.entry);
        btn_newlog = findViewById(R.id.newlog);
        btn_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QQmain.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(QQmain.this, "请登陆您的账户", Toast.LENGTH_SHORT).show();
                QQmain.this.finish();
            }
        });

        btn_newlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QQmain.this,RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(QQmain.this, "请注册您的账户", Toast.LENGTH_SHORT).show();
                QQmain.this.finish();
            }
        });




    }
}

