package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;

import io.github.salemlockwood.flyther.BuildConfig;
import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.utils.Colors;
import io.github.salemlockwood.flyther.utils.Screen;

public class GUI {

    private static final int NO_ACTION = -1;
    private static final int MENU_SCREEN = 0;
    private static final int SETTINGS_SCREEN = 1;
    private static final int LEARDBOARD_SCREEN = 2;
    private static final int CREDITS_SCREEN = 3;
    private static final int GAME_SCREEN = 4;

    private Screen screen;
    private Context context;
    private String gameNameString;
    private String newGameString;
    private String settingsString;
    private String leaderboardString;
    private String creditsString;
    private String backString;
    private String showUpdateRateString;
    private String enableMusicString;
    private String enableEffectString;

    public GUI(Screen screen, Context context){
        this.screen = screen;
        this.context = context;
        gameNameString = context.getString(R.string.app_name);
        newGameString = context.getString(R.string.newgame);
        settingsString = context.getString(R.string.settings);
        leaderboardString = context.getString(R.string.leadboard);
        creditsString = context.getString(R.string.credits);
        backString = context.getString(R.string.back);
        showUpdateRateString = context.getString(R.string.showUpdateRate);
        enableMusicString = context.getString(R.string.enableMusic);
        enableEffectString = context.getString(R.string.enableSound);
    }

    public void drawAt(Canvas canvas, int currentScreen){
        switch(currentScreen){
            case MENU_SCREEN:
                drawMenuScreen(canvas);
                break;
            case SETTINGS_SCREEN:
                drawSettingsScreen(canvas);
                break;
        }
    }

    private void drawSettingsScreen(Canvas canvas) {
        SharedPreferences sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID,Context.MODE_PRIVATE);
        Boolean showUpdateRate = sp.getBoolean("showUpdateRate",false);
        Boolean enableMusic = sp.getBoolean("enableMusic",true);
        Boolean enableSound = sp.getBoolean("enableSound",true);
        canvas.drawText(settingsString,150,250, Colors.getLogoColor(context));
        canvas.drawText(showUpdateRateString,150,400, (showUpdateRate ? Colors.getGuiColor(context) : Colors.getGuiDisabledColor(context)));
        canvas.drawText(enableMusicString,150,550, (enableMusic ? Colors.getGuiColor(context) : Colors.getGuiDisabledColor(context)));
        canvas.drawText(enableEffectString,150,700, (enableSound ? Colors.getGuiColor(context) : Colors.getGuiDisabledColor(context)));
        canvas.drawText(backString,150,850, Colors.getGuiColor(context));
    }

    public void drawMenuScreen(Canvas canvas){
        canvas.drawText(gameNameString,150,250, Colors.getLogoColor(context));
        canvas.drawText(newGameString,150,400, Colors.getGuiColor(context));
        canvas.drawText(settingsString,150,550, Colors.getGuiColor(context));
        canvas.drawText(leaderboardString,150,700, Colors.getGuiDisabledColor(context));
        canvas.drawText(creditsString,150,850, Colors.getGuiDisabledColor(context));
        canvas.drawText("BETA "+ BuildConfig.VERSION_NAME,10,screen.getHeight()-10, Colors.getBetaColor(context));
    }
    public int touchAt(int y){
        switch(screen.getCurrentScreen()){
            case MENU_SCREEN:
                if(y>300&&y<450){
                    return GAME_SCREEN;
                } else if(y>450&&y<600){
                    return SETTINGS_SCREEN;
                } else if(y>600&&y<750){
                    return LEARDBOARD_SCREEN;
                } else if(y>750&&y<900){
                    return CREDITS_SCREEN;
                }
                return NO_ACTION;
            case SETTINGS_SCREEN:
                if(y>300&&y<450){
                    switchUpdateRate();
                } else if(y>450&&y<600){
                    switchMusicEnabled();
                } else if(y>600&&y<750){
                    switchEffectEnabled();
                } else if(y>750&&y<900){
                    screen.setCurrentScreen(MENU_SCREEN);
                }
                return NO_ACTION;
        }
        return NO_ACTION;
    }

    private void switchUpdateRate() {
        SharedPreferences sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID,Context.MODE_PRIVATE);
        Boolean showUpdateRate = sp.getBoolean("showUpdateRate",false);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("showUpdateRate",!showUpdateRate);
        editor.commit();
    }

    private void switchMusicEnabled() {
        SharedPreferences sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID,Context.MODE_PRIVATE);
        Boolean enableMusic = sp.getBoolean("enableMusic",true);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("enableMusic",!enableMusic);
        editor.commit();
    }

    private void switchEffectEnabled() {
        SharedPreferences sp = context.getSharedPreferences(BuildConfig.APPLICATION_ID,Context.MODE_PRIVATE);
        Boolean enableSound = sp.getBoolean("enableSound",true);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("enableSound",!enableSound);
        editor.commit();
    }
}
