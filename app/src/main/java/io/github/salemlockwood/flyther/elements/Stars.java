package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.github.salemlockwood.flyther.utils.Screen;

public class Stars {
    private static final int GOLD_STAR = 0;
    private static final int SILVER_STAR = 1;
    private static final int BRONZE_STAR = 2;
    private static final int BRONZE_FACTOR = 50;
    private static final int SILVER_FACTOR = 35;
    private static final int GOLD_FACTOR = 15;

    private Screen screen;
    private Context context;
    private List<Star> stars;

    public Stars(Screen screen, Context context){
        this.screen = screen;
        this.context = context;
        stars = new ArrayList<>();
        tryStarsDeploy();
    }

    private void tryStarsDeploy() {
        ListIterator<Star> iterator = stars.listIterator();
        boolean bronzeDeploy = (Math.random() * 100) < BRONZE_FACTOR && !containsStar(stars,BRONZE_STAR);
        boolean silverDeploy = (Math.random() * 100) < SILVER_FACTOR && !containsStar(stars,SILVER_STAR);
        boolean goldDeploy = (Math.random() * 100) < GOLD_FACTOR && !containsStar(stars,GOLD_STAR);
        if(goldDeploy)
            iterator.add(new Star(screen,context,GOLD_STAR));
        else if(silverDeploy)
            iterator.add(new Star(screen,context,SILVER_STAR));
        else if(bronzeDeploy)
        iterator.add(new Star(screen,context,BRONZE_STAR));
    }

    private boolean containsStar(List<Star> stars, int startype) {
        for(Star star : stars){
            if(star.getStarType() == startype){
                return true;
            }
        }
        return false;
    }

    public void collidesWith(Plane plane){
        boolean tryStars = false;
        ListIterator iterator = stars.listIterator();
        while(iterator.hasNext()){
            Star s = (Star) iterator.next();
            if(s.collidesWith(plane)){
                s.fuelPlane(plane.getFuel());
                iterator.remove();
                tryStars = true;
            }
        }
        if(tryStars) tryStarsDeploy();
    }

    public void drawAt(Canvas canvas){
        for(Star star : stars)
            star.drawAt(canvas);
    }

    public void move(){
        boolean tryStars = false;
        ListIterator iterator = stars.listIterator();
        while(iterator.hasNext()){
            Star s = (Star) iterator.next();
            s.move();
            if(s.offScreen()){
                iterator.remove();
                tryStars = true;
            }
        }
        if(tryStars) tryStarsDeploy();
    }

}
