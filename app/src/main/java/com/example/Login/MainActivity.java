package com.example.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Tom_Jerry.R;

import java.util.Map;
public class MainActivity extends AppCompatActivity {
    //声明账号，密码，登录按钮，新用户注册按钮控件的变量，用于接收用户登录时输入的值
    private EditText et_user_name1,et_psw;
    private TextView tex_register,forget_psw,phone_login;
    private Button btn_login;
    private String userName,psw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置页面布局为我们之前写的登陆界面
        setContentView(R.layout.layout1);
        //2.设置此界面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3.写登录界面的业务逻辑
        init();
    }

    private void init(){
        //从Activity_main.xml中获取对应的UI组件
        et_user_name1 = findViewById(R.id.et_user_name1);
        et_psw = findViewById(R.id.et_psw1);
        btn_login = findViewById(R.id.btn_Login);
        tex_register = findViewById(R.id.tex_register);
        forget_psw=findViewById(R.id.forget_psw);
        phone_login=findViewById(R.id.phone_login);

        //在登陆界面显示刚刚注册的账号和密码
        //获取intent对象
        Intent intent = getIntent();
        //获取到键为userName,password的值
        String username = intent.getStringExtra("userName");
        String password = intent.getStringExtra("password");
        //如果username和password的值不为空，在登录界面显示
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            //显示登陆界面的账号和密码
            et_user_name1.setText(username);
            et_psw.setText(password);
        }

        //新用户注册按钮的点击事件
        tex_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册界面
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //手机号登录按钮的点击事件
        phone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到手机号登录界面
                Intent intent = new Intent(MainActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
            }
        });
        //忘记密码登录按钮的点击事件
        forget_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到忘记密码登录界面
                Intent intent = new Intent(MainActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });



        //登录按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先获取用户输入的账号和密码
                userName = et_user_name1.getText().toString().trim();
                psw = et_psw.getText().toString().trim();

                //获取文件名为SEND的对象
                SharedPreferences sharedPreferences = getSharedPreferences("SEND",MODE_PRIVATE);

                //将sharedPreferences转换为一个map集合，通过遍历map集合来判断账号和密码是否已经注册过
                Map<String,?> map = sharedPreferences.getAll();

                //判断账号密码是否有效
                if(usernameIsValid(map, userName,psw)){
                    //账号和密码分别和map中的key和value一致，则登陆成功
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //销毁登陆界面
                    MainActivity.this.finish();
                    //跳转功能界面
                    Intent intent = new Intent(MainActivity.this, com.example.Tom_Jerry.MainActivity.class);
                    intent.putExtra("username",userName);
                    startActivity(intent);
                }
            }
        });
    }


    private boolean usernameIsValid(Map<String,?>map,String userName,String psw){
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(MainActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(psw)){//密码为空的情况
            Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();

        }
        else {//否则遍历map集合中的每个key来判断用户输入的账号和其是否相同
            for (Map.Entry<String,?>m : map.entrySet()){
                if (m.getKey().equals(userName)){
                    if(m.getValue().equals(psw)){

                        return true;
                    }
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            Toast.makeText(MainActivity.this, "查无此人", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}