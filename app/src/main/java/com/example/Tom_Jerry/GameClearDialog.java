package com.example.Tom_Jerry;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class GameClearDialog extends Dialog {

    //需要操作的控件
    private TextView gameclearScore;

    private Button gameclearReplayBtn,gameclearExitBtn;

    public GameClearDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置显示内容
        setContentView(R.layout.clear_dialog);
        //分数
        gameclearScore = findViewById(R.id.gameclear_score);

        gameclearReplayBtn = findViewById(R.id.gameclear_replay);
        gameclearExitBtn = findViewById(R.id.gameclear_exitBtn);
        //被按钮绑定事件
        gameclearReplayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新激活游戏界面

                Intent intent = new Intent(getContext(), ClearActivity.class);
                getContext().startActivity(intent);
                System.exit(1);
            }
        });
        gameclearExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到游戏的开始界面
                System.exit(1);
             /*   Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);*/
            }
        });
    }

    public TextView getGameclaerScore() {
        return gameclearScore;
    }
}

