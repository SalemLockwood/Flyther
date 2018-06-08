package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.utils.Screen;

public class Star {
    private static final int GOLD_STAR = 0;
    private static final int SILVER_STAR = 1;
    private static final int BRONZE_STAR = 2;
    private static final int BRONZE_FUEL_AMOUNT = 225;
    private static final int SILVER_FUEL_AMOUNT = 450;
    private static final int GOLD_FUEL_AMOUNT = 900;
    private static final int DANCE_FACTOR = 500;
    private static final int GOING_UP = -5;
    private static final int GOING_DOWN = 5;


    private Screen screen;
    private Bitmap bp;
    private Context context;
    private int altitude;
    private int position;
    private int higherFactor;
    private int lowerFactor;
    private int starType;
    private int goingVertical;

    public Star(Screen screen, Context context,int starType){
        this.screen = screen;
        this.altitude = screen.getHeight()/2;
        this.position = screen.getWidth() + 10;
        this.goingVertical = (((Math.random()*100)>=50) ? GOING_UP : GOING_DOWN);
        this.higherFactor = screen.getHeight()/2-(DANCE_FACTOR/2);
        this.lowerFactor = screen.getHeight()/2+(DANCE_FACTOR/2);
        this.starType = starType;
        switch (this.starType){
            case BRONZE_STAR:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_bronze);
                break;
            case SILVER_STAR:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_silver);
                break;
            case GOLD_STAR:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_gold);
                break;
        }
    }

    public void drawAt(Canvas canvas){
        canvas.drawBitmap(bp,position,altitude,null);
    }

    public void fuelPlane(Fuel fuel){
        switch (this.starType){
            case BRONZE_STAR:
                fuel.fuelSupply(BRONZE_FUEL_AMOUNT);
                break;
            case SILVER_STAR:
                fuel.fuelSupply(SILVER_FUEL_AMOUNT);
                break;
            case GOLD_STAR:
                fuel.fuelSupply(GOLD_FUEL_AMOUNT);
                break;
        }
    }

    public boolean offScreen(){
        return this.position+100 < 0;
    }

    public int getStarType() {
        return this.starType;
    }

    public boolean collidesWith(Plane plane){
        Point p = new Point(100,plane.getAltitude());
        Point s = new Point(this.position,this.altitude);
        int p_r = 100;
        int s_r = 20;
        double d = Math.sqrt((Math.pow(p.x - s.x, 2)+Math.pow(p.y - s.y, 2)));
        return d<p_r+s_r;
    }

    public void move() {
        altitude += goingVertical;
        if(goingVertical==GOING_UP && altitude <= higherFactor){
            goingVertical = GOING_DOWN;
        }
        if(goingVertical==GOING_DOWN && altitude >= lowerFactor){
            goingVertical = GOING_UP;
        }
        this.position -= 5;
    }
}
