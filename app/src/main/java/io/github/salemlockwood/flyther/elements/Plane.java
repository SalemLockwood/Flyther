package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.List;

import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.engine.Sound;
import io.github.salemlockwood.flyther.utils.Screen;

public class Plane {

    private static final int X = 100;
    private static final int RADIUS = 50;
    private static int THRUST = -20;
    private static final int PLANE_BLUE = 0;
    private static final int PLANE_GREEN = 1;
    private static final int PLANE_RED = 2;
    private static final int PLANE_YELLOW = 3;

    private int altitude;
    private int speed;
    private Screen screen;
    private Context context;
    private Sound sound;
    private List<Bitmap> plane;
    private Bitmap expsprite;
    private final Bitmap nucsprite;
    private Fuel fuel;

    public Plane(Screen screen, Context context, int planeColor, Fuel fuel){
        this.altitude = 200;
        this.speed = THRUST/2;
        this.screen = screen;
        this.context = context;
        this.fuel = fuel;
        sound = new Sound(context);
        plane = new ArrayList<>();
        populatePlane(planeColor);
        expsprite = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion_transparent);
        nucsprite = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.nuclear_explosion);
    }

    private void populatePlane(int planeColor) {

        switch (planeColor){
            case PLANE_BLUE:
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeblue1),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeblue2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeblue3),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeblue2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                break;
            case PLANE_GREEN:
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planegreen1),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planegreen2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planegreen3),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planegreen2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                break;
            case PLANE_RED:
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planered1),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planered2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planered3),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planered2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                break;
            case PLANE_YELLOW:
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeyellow1),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeyellow2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeyellow3),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                plane.add(Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.planeyellow2),
                        RADIUS*2,
                        RADIUS*2,
                        false
                ));
                break;
        }
    }

    public void drawAt(Canvas canvas, int planePropPos){

        Matrix m = new Matrix();
        m.postRotate(speed);
        Bitmap rotatedPlane = Bitmap.createBitmap(plane.get(planePropPos), 0, 0,
                plane.get(planePropPos).getWidth(), plane.get(planePropPos).getHeight(), m, true);

        canvas.drawBitmap(rotatedPlane,X-50,altitude-50,null);
    }

    public void fall() {
        boolean hitGround = this.altitude + RADIUS > screen.getHeight() - 70;
        if(!hitGround) {
            this.altitude += speed;
            speed++;
        }
    }

    public int getAltitude(){
        return altitude;
    }

    public void fly(){
        boolean hitTop = altitude - RADIUS < 0;
        if(!hitTop) {
            sound.play();
            //this.altitude -= 150;
            this.speed = THRUST;
        }
    }

    public void dead(int currentFrame, Canvas canvas, int planePropPos){
        boolean hitGround = this.altitude + RADIUS > screen.getHeight() - 70;
        Bitmap explosion = (hitGround ? nucsprite : expsprite);
        int EXP_COLS = 5;
        int EXP_ROWS = 5;
        int SPRITE_WIDTH = explosion.getWidth() / EXP_COLS;
        int SPRITE_HEIGHT = explosion.getHeight() / EXP_ROWS;
        int start_x = currentFrame%EXP_COLS * SPRITE_WIDTH;
        int start_y = currentFrame/EXP_ROWS * SPRITE_HEIGHT;
        Bitmap bp = Bitmap.createBitmap(explosion,start_x,start_y,SPRITE_WIDTH,SPRITE_HEIGHT);
        int x_f = hitGround ? X-50-currentFrame*5 : X-50;
        int y_f = altitude - 50;

        if(currentFrame < 15) {
            Matrix m = new Matrix();
            m.postRotate(speed);
            Bitmap rotatedPlane = Bitmap.createBitmap(plane.get(planePropPos), 0, 0,
                    plane.get(planePropPos).getWidth(), plane.get(planePropPos).getHeight(), m, true);

            canvas.drawBitmap(rotatedPlane, x_f, y_f, null);
        }

        canvas.drawBitmap(bp,x_f,y_f,null);
    }

    public int getRadius() {
        return RADIUS;
    }

    public Fuel getFuel(){
        return this.fuel;
    }
}
