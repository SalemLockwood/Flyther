package io.github.salemlockwood.flyther.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class Screen {

    private DisplayMetrics metrics;
    private int currentScreen = 0;

    public Screen(Context context){
        WindowManager wm = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public int getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(int currentScreen) {
        this.currentScreen = currentScreen;
    }

    public int getHeight(){
        return metrics.heightPixels;
    }

    public int getWidth(){
        return metrics.widthPixels;
    }
}
