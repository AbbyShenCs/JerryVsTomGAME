package com.example.Tom_Jerry;


import static com.example.Tom_Jerry.MusicServer.restart;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

/**
 * 游戏暂停的对话框
 */
public class GamePauseDialog extends Dialog {
    /**
     * 对话框中的两个按钮
     */

    private Button replayBtn,pause_playBtn;
    //自定义视图
    private hua GameView;

   /* public GamePauseDialog(@NonNull Context context) {
        super(context);
    }*/

    public GamePauseDialog(@NonNull Context context,hua gameView) {
        super(context);
        this.GameView= gameView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示自定义布局
        setContentView(R.layout.game_pause);
        //获得按钮的对象
        pause_playBtn = findViewById(R.id.pause_playBtn);
        replayBtn = findViewById(R.id.pause_replayBtn);
        //给两个按钮绑定点击事件
        //继续游戏
        pause_playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my.ispause=true;
                GamePauseDialog.this.dismiss();
                restart();
            }
        });
        //重新开始游戏
        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("重新开始游戏");
                //把当前的对话框关闭
                GamePauseDialog.this.dismiss();
                Intent intent=new Intent(getContext(), MainActivity.class);
                //重新激活游戏界面
                getContext().startActivity(intent);
                //重新加载游戏界面
                System.exit(0);

            }
        });


    }




}
