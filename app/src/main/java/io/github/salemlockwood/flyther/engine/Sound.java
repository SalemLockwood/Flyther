package io.github.salemlockwood.flyther.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import io.github.salemlockwood.flyther.BuildConfig;
import io.github.salemlockwood.flyther.R;

public class Sound {

    private SoundPool soundPool;
    private Context context;
    private int engineStart;
    private boolean soundEnabled = true;

    public Sound(Context context){
        this.context = context;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            soundPool = new SoundPool.Builder().setMaxStreams(3).build();
        }else
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        engineStart = soundPool.load(context, R.raw.fly, 0);
        SharedPreferences sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID,Context.MODE_PRIVATE);
        soundEnabled = sp.getBoolean("enableSound",true);
    }

    public void play(){
        if(soundEnabled)
            soundPool.play(engineStart,1,1,1,0,1);
    }
}
