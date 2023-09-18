package com.example.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Tom_Jerry.R;
public class RegisterActivity extends AppCompatActivity {
    //声明账号，密码，再次输入密码，单选组合框，注册按钮的控件变量，用于接收用户输入的值
    private EditText et_user_name2,et_psw2,et_psw2_again,phone_number;
    private RadioGroup GenderRadio;
    private Button affirm;

    //声明控件对应的字符串变量
    private String userName,psw,pswAgain,Phone_number;
    /*
    1、onCreate方法是在activity初始化时调用的，可以在这写业务逻辑
    2、子类在重写onCreate方法时必须调用父类的onCreate方法，即super.onCreate(savedInstanceState)
    3、通常情况下，我们需要在onCreate()方法中调用setContentView(int)方法来制定页面布局以填充页面UI
    * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1、设置页面布局为我们之前写的注册页面
        setContentView(R.layout.layout2);
        //2、设置页面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3、实现注册界面的业务逻辑
        init();
    }
    public void init(){
        //1、从activity_register获取对应的UI控件
        et_user_name2 = findViewById(R.id.et_user_name2);
        et_psw2 = findViewById(R.id.et_psw2);
        et_psw2_again = findViewById(R.id.et_psw2_again);
        phone_number=findViewById(R.id.phone_number);
        GenderRadio = findViewById(R.id.GenderRadio);
        affirm = findViewById(R.id.affirm);
        //2、实现注册按钮的监听器，点击注册即可触发监听器
        affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、获取输入在用户名，密码，再次输入密码文本对应的字符串
                getEditString();
                //2、获取单选按钮的值
                int sex;
                int sexChoseId = GenderRadio.getCheckedRadioButtonId();
                switch (sexChoseId){
                    case R.id.girl:
                        sex = 0;
                        break;

                    case R.id.boy:
                        sex = 1;
                        break;

                    default:
                        sex = -1;
                        break;
                }
                //3、判断用户输入的值是否为空
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"请输入账号!",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"请输入密码!",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"请再次输入密码!",Toast.LENGTH_SHORT).show();
                }else if(sex < 0){
                    Toast.makeText(RegisterActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this,"请两次输入一样的密码!",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(Phone_number)){
                    Toast.makeText(RegisterActivity.this,"请输入正确的手机号!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    /*4.注册成功后把账号和密码保存到loginActivity，并返回到登录界面*/
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    intent.putExtra("userName",userName);
                    intent.putExtra("password",psw);
                    intent.putExtra("Phone",Phone_number);
                    startActivity(intent);
                    //通过sharedpreferences保存账号、密码和手机号
                    SharedPreferences sharedPreferences =getSharedPreferences("SEND", Context.MODE_PRIVATE);
                    SharedPreferences sharedPreferences1 =getSharedPreferences("PHONE1", Context.MODE_PRIVATE);
                    SharedPreferences sharedPreferences2 =getSharedPreferences("PHONE2", Context.MODE_PRIVATE);
                    //获取edit对象
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    SharedPreferences.Editor edit2 = sharedPreferences2.edit();
                    //账号、密码、手机号两两匹配
                    edit.putString(userName,psw);
                    edit1.putString(userName,Phone_number);
                    edit2.putString(Phone_number,psw);
                    //数据写入内存
                    edit.apply();
                    edit1.apply();
                    edit2.apply();
                    //销毁activity
                    RegisterActivity.this.finish();
                    Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getEditString() {
        userName = et_user_name2.getText().toString().trim();
        psw = et_psw2.getText().toString().trim();
        pswAgain = et_psw2_again.getText().toString().trim();
        Phone_number = phone_number.getText().toString().trim();
    }
}
