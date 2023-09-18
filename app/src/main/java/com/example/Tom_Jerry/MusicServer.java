package com.example.Tom_Jerry;


import android.content.Context;
import android.media.MediaPlayer;

public class MusicServer{
    private static MediaPlayer mp =null;
    public static void play(Context context, int resource){
        stop(context);
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
    }
    public static void stop(Context context) {
        // TODO Auto-generated method stub
        if(mp!= null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public static void pause() {
        // TODO Auto-generated method stub
        if(mp!= null){
            mp.pause();
        }
    }

    public static void restart() {
        // TODO Auto-generated method stub
        if(mp!= null){
            mp.start();
        }
    }

}

