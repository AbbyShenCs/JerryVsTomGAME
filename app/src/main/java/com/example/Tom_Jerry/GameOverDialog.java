package com.example.Tom_Jerry;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class GameOverDialog extends Dialog {

    //需要操作的控件
    private TextView gameoverScore;

    private Button gameoverReplayBtn,gameoverExitBtn;

    public GameOverDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置显示内容
        setContentView(R.layout.dialog_gameover);
        //分数
        gameoverScore = findViewById(R.id.gameover_score);

        gameoverReplayBtn = findViewById(R.id.gameover_replay);
        gameoverExitBtn = findViewById(R.id.gameover_exitBtn);
        //被按钮绑定事件
        gameoverReplayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新激活游戏界面

                Intent intent = new Intent(getContext(), GameActivity.class);
                getContext().startActivity(intent);
                System.exit(1);
            }
        });
        gameoverExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到游戏的开始界面
                System.exit(1);
             /*   Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);*/
            }
        });
    }

    public TextView getGameoverScore() {
        return gameoverScore;
    }
}

