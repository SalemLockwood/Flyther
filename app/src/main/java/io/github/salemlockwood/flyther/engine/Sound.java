package io.github.salemlockwood.flyther.engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import io.github.salemlockwood.flyther.R;

public class Sound {

    private SoundPool soundPool;
    private Context context;
    private int engineStart;

    public Sound(Context context){
        this.context = context;
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        engineStart = soundPool.load(context, R.raw.fly, 0);
    }

    public void play(){
        soundPool.play(engineStart,1,1,1,0,1);
    }
}
