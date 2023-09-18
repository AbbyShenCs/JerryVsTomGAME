package com.example.Tom_Jerry;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

public final class MusicUtil {
    public MusicUtil() {
    }

    public static SoundPool soundPool=new SoundPool(100, AudioManager.STREAM_MUSIC,0);
    public static Map<Integer,Integer> sounds = new HashMap<Integer, Integer>();
    public static void initMusic(Context context){
        sounds.put(1,soundPool.load(context, R.raw.bgmusic,1));
        sounds.put(2,soundPool.load(context,R.raw.bgmusic1,1));
        sounds.put(3,soundPool.load(context,R.raw.bgmusic2,1));
        sounds.put(4,soundPool.load(context,R.raw.bgmusic3,1));

    }
}
