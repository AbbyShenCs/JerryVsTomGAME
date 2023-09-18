package com.example.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Tom_Jerry.R;
public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {
    //声明计算器的变量
    private TextView tv_title;
    private EditText et_show;
    private Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,
    btn_add,btn_subtract,btn_multiply,btn_divide,btn_point,btn_equal,btn_clear;
    //设置标志位来区分加减乘除
    private int flag = 0;
    //设置参与计算的第一个数和第二个数，初始化为“0”
    private String text1 = "0",text2 = "0";


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //1.设置页面布局为我们之前写的计算器界面
        setContentView(R.layout.layout3);

        //2.获取在MainActivity中定义的intent对象
        Intent intent = getIntent();

        //3.根据key获取intent对象中的value
        String title = intent.getStringExtra("username")+"的计算器";

        //4.获得各个组件
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);
        et_show = findViewById(R.id.et_show);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_add = findViewById(R.id.btn_add);
        btn_subtract = findViewById(R.id.btn_subtract);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_point = findViewById(R.id.btn_point);
        btn_equal = findViewById(R.id.btn_equal);
        btn_clear = findViewById(R.id.btn_clear);

        //5.为各个按钮的点击事件设置监听器
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    //绑定完之后，在onClick方法中写入事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_0:
                et_show.append("0");
                break;
            case R.id.btn_1:
                et_show.append("1");
                break;
            case R.id.btn_2:
                et_show.append("2");
                break;
            case R.id.btn_3:
                et_show.append("3");
                break;
            case R.id.btn_4:
                et_show.append("4");
                break;
            case R.id.btn_5:
                et_show.append("5");
                break;
            case R.id.btn_6:
                et_show.append("6");
                break;
            case R.id.btn_7:
                et_show.append("7");
                break;
            case R.id.btn_8:
                et_show.append("8");
                break;
            case R.id.btn_9:
                et_show.append("9");
                break;

            case R.id.btn_add:
                flag = 1;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;

            case R.id.btn_subtract:
                flag = 2;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;

            case R.id.btn_multiply:
                flag = 3;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;

            case R.id.btn_divide:
                flag = 4;
                text1 = et_show.getText().toString();
                et_show.setText("");
                break;

            case R.id.btn_point:
                et_show.append(".");
                break;

            case R.id.btn_equal://当点击等号，会将之前的et_show中的文本内容保存到text2,并且根据flag的值来判断加减乘除
                switch (flag){
                    case 0:
                        et_show.append("0");
                        break;
                    case 1:
                        text2 = et_show.getText().toString();
                        Double res = Double.parseDouble(text1) + Double.parseDouble(text2);
                        et_show.setText(res + "");
                        break;

                    case 2:
                        text2 = et_show.getText().toString();
                        Double res2 = Double.parseDouble(text1) - Double.parseDouble(text2);
                        et_show.setText(res2 + "");
                        break;

                    case 3:
                        text2 = et_show.getText().toString();
                        Double res3 = Double.parseDouble(text1) * Double.parseDouble(text2);
                        et_show.setText(res3 + "");
                        break;

                    case 4:
                        text2 = et_show.getText().toString();
                        Double res4 = Double.parseDouble(text1) / Double.parseDouble(text2);
                        et_show.setText(res4 + "");
                        break;

                    default:
                        break;

                }
                break;
            case R.id.btn_clear:
                flag = 0;
                text1 = "0";
                text2 = "0";
                et_show.setText("");
                break;
            default:
                break;

        }
    }
}
