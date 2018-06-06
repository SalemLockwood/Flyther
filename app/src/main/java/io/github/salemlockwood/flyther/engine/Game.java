package io.github.salemlockwood.flyther.engine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.elements.GUI;
import io.github.salemlockwood.flyther.elements.GameOver;
import io.github.salemlockwood.flyther.elements.Ground;
import io.github.salemlockwood.flyther.elements.Grounds;
import io.github.salemlockwood.flyther.elements.Plane;
import io.github.salemlockwood.flyther.elements.Score;
import io.github.salemlockwood.flyther.elements.Spike;
import io.github.salemlockwood.flyther.elements.Spikes;
import io.github.salemlockwood.flyther.utils.Screen;

public class Game extends SurfaceView implements Runnable,View.OnTouchListener {

    private static final int MENU_SCREEN = 0;
    private static final int SETTINGS_SCREEN = 1;
    private static final int LEARDBOARD_SCREEN = 2;
    private static final int CREDITS_SCREEN = 3;
    private static final int GAME_SCREEN = 4;
    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Plane plane;
    private Bitmap background;
    private Screen screen;
    private Spike spike;
    private Spikes spikes;
    private Score score;
    private int planeColor;
    private int propellerPosition = 0;
    private Context context;
    private Grounds grounds;
    private GUI gui;
    private int weather = 0;

    public Game(Context context) {
        super(context);
        initializeElements(context);
        setOnTouchListener(this);
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(isRunning) {
            switch (screen.getCurrentScreen()){
                case MENU_SCREEN:
                    execGuiAction(this.gui.touchAt((int) event.getY()));
                    break;
                case GAME_SCREEN:
                    plane.fly();
                    break;
            }
        }
        else{
            initializeElements(context);
            screen.setCurrentScreen(0);
            new Thread(this).start();
        }
        return false;
    }

    private void execGuiAction(int i) {
        switch(i){
            case GAME_SCREEN:
                isRunning = true;
                screen.setCurrentScreen(GAME_SCREEN);
                break;
            case SETTINGS_SCREEN:
                Toast.makeText(context, context.getText(R.string.underDevelopment), Toast.LENGTH_SHORT).show();
                break;
            case LEARDBOARD_SCREEN:
                Toast.makeText(context, context.getText(R.string.underDevelopment), Toast.LENGTH_SHORT).show();
                break;
            case CREDITS_SCREEN:
                Toast.makeText(context, context.getText(R.string.underDevelopment), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void run() {
        while(isRunning){
            if(!holder.getSurface().isValid()) continue;
            Canvas canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);

            if(screen.getCurrentScreen() == GAME_SCREEN) {
                drawGameScreen(canvas);
            } else {
                gui.drawAt(canvas,screen.getCurrentScreen());
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawGameScreen(Canvas canvas) {
        propellerPosition++;
        if(propellerPosition>3) propellerPosition = 0;
        plane.drawAt(canvas,propellerPosition);
        plane.fall();
        spikes.move();
        spikes.drawAt(canvas);
        grounds.drawAt(canvas);
        grounds.move();
        score.drawAt(canvas);

        if((new CollisionVerifier(plane,spikes,screen).haveCrashed())){
            new GameOver(screen,context).drawAt(canvas);
            isRunning = false;
        }
    }

    private void initializeElements(Context context){
        screen = new Screen(context);
        score = new Score(context);
        this.planeColor = (int) (Math.random() * 4);
        this.weather = (int) (Math.random() * 5);
        plane = new Plane(screen,context,planeColor);
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(back, back.getWidth(), screen.getHeight(), false);
        this.spike = new Spike(screen, 200,context,weather);
        this.spikes = new Spikes(screen,score,context,weather);
        this.grounds = new Grounds(screen,context,weather);
        this.gui = new GUI(screen,context);
        isRunning = true;
    }

    public void pause(){
        isRunning = false;
    }

    public void inicia(){
        isRunning = true;
    }
}
