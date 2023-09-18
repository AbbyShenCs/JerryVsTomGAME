package com.example.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Tom_Jerry.R;

import java.util.Map;
public class PhoneLoginActivity extends AppCompatActivity {
    private EditText phone,et_psw;
    private Button btn_Login;
    private String PhoneNumber,psw,username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置页面布局为我们之前写的登陆界面
        setContentView(R.layout.layout6);
        //2.设置此界面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3.写登录界面的业务逻辑
        init();
    }
    private void init(){
        //1.phone_login.xml中获取对应的UI组件
        phone = findViewById(R.id.phone_number_login);
        et_psw = findViewById(R.id.et_psw_phone);
        btn_Login = findViewById(R.id.btn_Login);
        //4.登录按钮的点击事件
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先获取用户输入的账号和密码
                PhoneNumber = phone.getText().toString().trim();
                psw = et_psw.getText().toString().trim();

                //获取文件名为PHONE2的对象
                SharedPreferences sharedPreferences = getSharedPreferences("PHONE2",MODE_PRIVATE);
                SharedPreferences sharedPreferences1 = getSharedPreferences("PHONE1",MODE_PRIVATE);

                //将sharedPreferences转换为一个map集合，通过遍历map集合来判断账号和密码是否已经注册过
                Map<String,?> map = sharedPreferences.getAll();
                Map<String,?> map1 = sharedPreferences1.getAll();
                //遍历map1，确定键值对username-phone的对应值，得到username
                for (Map.Entry<String,?>m1 : map1.entrySet()){
                    if (m1.getValue().equals(PhoneNumber)){
                        username = m1.getKey();
                    }
                }

                //判断账号密码是否有效
                if(usernameIsValid(map, PhoneNumber,psw)){
                    //账号和密码分别和map中的key和value一致，则登陆成功
                    Toast.makeText(PhoneLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //销毁登陆界面
                    PhoneLoginActivity.this.finish();
                    //跳转功能界面
                    Intent intent = new Intent(PhoneLoginActivity.this,CalculateActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean usernameIsValid(Map<String,?>map,String PhoneNumber,String psw){
        if (TextUtils.isEmpty(PhoneNumber)){
            Toast.makeText(PhoneLoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(psw)){//密码为空的情况
            Toast.makeText(PhoneLoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }
        else {//否则遍历map集合中的每个key来判断用户输入的账号和其是否相同
            for (Map.Entry<String,?>m : map.entrySet()){
                if (m.getKey().equals(PhoneNumber)){
                    if(m.getValue().equals(psw)){
                        return true;
                    }
                    Toast.makeText(PhoneLoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            Toast.makeText(PhoneLoginActivity.this, "查无此人", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
