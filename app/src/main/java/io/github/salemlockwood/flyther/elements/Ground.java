package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.utils.Screen;

public class Ground {

    private static final int GROUND_WIDTH = 800;
    private static final int GROUND_HEIGHT = 71;
    private static final int DIRTY = 0;
    private static final int GRASS = 1;
    private static final int ICE = 2;
    private static final int ROCK = 3;
    private static final int SNOW = 4;

    private Screen screen;
    private Context context;
    private int position;
    private Bitmap ground;
    private final int weather;
    private Bitmap bp;

    public Ground(Screen screen, Context context, int position, int weather){
        this.screen = screen;
        this.context = context;
        this.position = position;
        this.weather = weather;
        switch(weather){
            case DIRTY:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.grounddirt);
                break;
            case GRASS:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.groundgrass);
                break;
            case ICE:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.groundice);
                break;
            case ROCK:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.groundrock);
                break;
            case SNOW:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.groundsnow);
                break;
        }
        ground = bp.createScaledBitmap(bp,GROUND_WIDTH, GROUND_HEIGHT, false);
    }
    public void drawAt(Canvas canvas){
        canvas.drawBitmap(ground,position,screen.getHeight()-71,null);
    }
    public void move(){
        this.position -= 5;
    }
    public boolean offScreen(){
        return this.position + GROUND_WIDTH < 0;
    }

    public int getPosition() {
        return this.position;
    }
}
