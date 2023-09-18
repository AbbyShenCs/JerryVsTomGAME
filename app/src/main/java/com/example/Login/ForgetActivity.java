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
public class ForgetActivity extends AppCompatActivity {
    private EditText f_username,f_phone;
    private Button f_back;
    private String UserName,PhoneNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置页面布局为我们之前写的登陆界面
        setContentView(R.layout.layout5);
        //2.设置此界面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3.写登录界面的业务逻辑
        init();
    }
    private void init(){
        //layout5中获取对应的UI组件
        f_username = findViewById(R.id.find_username);
        f_phone = findViewById(R.id.find_phone_number);
        f_back = findViewById(R.id.find_psw);

        //找回密码按钮的点击事件
        f_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先获取用户输入的账号和密码
                UserName = f_username.getText().toString().trim();
                PhoneNumber = f_phone.getText().toString().trim();

                //获取文件名为PHONE1的对象
                SharedPreferences sharedPreferences = getSharedPreferences("PHONE1",MODE_PRIVATE);

                //将sharedPreferences转换为一个map集合，通过遍历map集合来判断账号和密码是否已经注册过
                Map<String,?> map = sharedPreferences.getAll();

                //判断账号密码是否有效
                if(usernameIsValid(map, UserName,PhoneNumber)){
                    //账号和密码分别和map中的key和value一致，则登陆成功
                    Toast.makeText(ForgetActivity.this, "比对成功", Toast.LENGTH_SHORT).show();
                    //销毁登陆界面
                    ForgetActivity.this.finish();
                    //跳转功能界面
                    Intent intent = new Intent(ForgetActivity.this,BackActivity.class);
                    intent.putExtra("UserName",UserName);
                    intent.putExtra("PhoneNumber",PhoneNumber);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean usernameIsValid(Map<String,?>map,String userName,String PhoneNumber){
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(ForgetActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(PhoneNumber)){//密码为空的情况
            Toast.makeText(ForgetActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
        }
        else {//否则遍历map集合中的每个key来判断用户输入的账号和其是否相同
            for (Map.Entry<String,?>m : map.entrySet()){
                if (m.getKey().equals(userName)){
                    if(m.getValue().equals(PhoneNumber)){
                        return true;
                    }
                    Toast.makeText(ForgetActivity.this, "比对失败", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            Toast.makeText(ForgetActivity.this, "查无此人", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
