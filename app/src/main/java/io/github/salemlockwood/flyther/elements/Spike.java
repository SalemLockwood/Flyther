package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.utils.Colors;
import io.github.salemlockwood.flyther.utils.Screen;

public class Spike {

    private static final int SPIKE_SIZE = 400;
    private static final int SPIKE_WIDTH = 200;
    private static final int DIRTY = 0;
    private static final int GRASS = 1;
    private static final int ICE = 2;
    private static final int ROCK = 3;
    private static final int SNOW = 4;

    private final Screen screen;
    private final int spikeChalange;
    private int position;
    private int bottomSpikeHeight;
    private int higherSpikeHeight;
    private Context context;
    private Bitmap spiketop;
    private Bitmap spikebottom;
    private boolean collisionEnabled = true;
    private Bitmap bpt;
    private Bitmap bpb;

    public Spike(Screen screen, int position, Context context, int weather){
        this.screen = screen;
        this.position = position;
        this.spikeChalange = randomValue();
        this.bottomSpikeHeight = screen.getHeight() - SPIKE_SIZE + this.spikeChalange;
        this.higherSpikeHeight = SPIKE_SIZE + this.spikeChalange;
        this.context = context;

        switch (weather){
            case DIRTY:
                bpb = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockdirty);
                bpt = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockdirtydown);
                break;
            case GRASS:
                bpb = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockgrass);
                bpt = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockgrassdown);
                break;
            case ICE:
                bpb = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockice);
                bpt = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockicedown);
                break;
            case ROCK:
                bpb = BitmapFactory.decodeResource(context.getResources(), R.drawable.rock);
                bpt = BitmapFactory.decodeResource(context.getResources(), R.drawable.rockdown);
                break;
            case SNOW:
                bpb = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocksnow);
                bpt = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocksnowdown);
                break;
        }

        spikebottom = Bitmap.createScaledBitmap(bpb,SPIKE_WIDTH,bottomSpikeHeight,false);
        spiketop = Bitmap.createScaledBitmap(bpt,SPIKE_WIDTH,higherSpikeHeight,false);
    }

    public int getPosition(){
        return position;
    }

    private int randomValue() {
        return (int) (Math.random() * 250) - 125;
    }

    public boolean offScreen(){
        return this.position + SPIKE_WIDTH < 0;
    }

    public void drawAt(Canvas canvas){
        drawBottomSpikeAt(canvas);
        drawTopSpikeAt(canvas);
    }

    public void move(){
        position-=5;
    }

    private void drawTopSpikeAt(Canvas canvas) {
        canvas.drawBitmap(spiketop,position-100,0,null);
    }

    private void drawBottomSpikeAt(Canvas canvas) {
        canvas.drawBitmap(spikebottom,position-100,bottomSpikeHeight,null);
    }

    public boolean hasCollisionWith(Plane plane) {
        return hasCollisionWithTopSpike(plane) || hasCollisionWithBottomSpike(plane);
    }

    private boolean hasCollisionWithTopSpike(Plane plane) {
        return ((plane.getAltitude()-plane.getRadius()) < higherSpikeHeight) && (position == 130) && collisionEnabled;
    }

    public boolean hasCollisionWithBottomSpike(Plane plane){
        return ((plane.getAltitude()+plane.getRadius()) > bottomSpikeHeight) && (position == 130) && collisionEnabled;

    }
}
