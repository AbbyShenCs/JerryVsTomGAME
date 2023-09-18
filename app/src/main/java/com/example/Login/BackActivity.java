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

public class BackActivity extends AppCompatActivity {
    private EditText change_psw,change_psw_again;
    private Button affirm_change;
    private String Psw,PswAgain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.设置页面布局为我们之前写的登陆界面
        setContentView(R.layout.layout4);
        //2.设置此界面为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //3.写登录界面的业务逻辑
        init();
    }
    private void init(){
        change_psw = findViewById(R.id.change_psw);
        change_psw_again = findViewById(R.id.change_psw_again);
        affirm_change = findViewById(R.id.affirm_change);
        //获取到键为userName,password的值
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        String phone_number = intent.getStringExtra("PhoneNumber");

        affirm_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Psw = change_psw.getText().toString().trim();
                PswAgain = change_psw_again.getText().toString().trim();
                if(TextUtils.isEmpty(Psw)){
                    Toast.makeText(BackActivity.this,"请输入密码!",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(PswAgain)){
                    Toast.makeText(BackActivity.this,"请再次输入密码!",Toast.LENGTH_SHORT).show();
                }else if(!Psw.equals(PswAgain)){
                    Toast.makeText(BackActivity.this,"请两次输入一样的密码!",Toast.LENGTH_SHORT).show();
                }else{

                    //获取文件名为SEND的对象
                    SharedPreferences sharedPreferences = getSharedPreferences("SEND",MODE_PRIVATE);
                    SharedPreferences sharedPreferences1 = getSharedPreferences("PHONE2",MODE_PRIVATE);

                    //将sharedPreferences转换为一个map集合，通过遍历map集合来判断账号和密码是否已经注册过
                    Map<String,?> map = sharedPreferences.getAll();
                    Map<String,?> map1 = sharedPreferences1.getAll();
                    for (Map.Entry<String,?>m : map.entrySet()){
                        if (m.getKey().equals(username)){
                            //获取edit对象
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            //清除键值对并重新储存
                            edit.remove(username);
                            edit.putString(username,Psw);
                            edit.apply();
                        }
                    }
                    for (Map.Entry<String,?>m1 : map1.entrySet()){
                        if (m1.getKey().equals(phone_number)){
                            //获取edit对象
                            SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                            //清除键值对并重新储存
                            edit1.remove(phone_number);
                            edit1.putString(phone_number,Psw);
                            edit1.apply();
                        }
                    }
                    Toast.makeText(BackActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                    /*4.修改成功后把账号和密码以及手机号保存到，并返回到登录界面*/
                    Intent intent = new Intent(BackActivity.this,MainActivity.class);
                    intent.putExtra("userName",username);
                    intent.putExtra("password",Psw);
                    intent.putExtra("Phone",phone_number);
                    startActivity(intent);
                    //销毁activity
                    BackActivity.this.finish();
                }
            }
        });
    }

}
