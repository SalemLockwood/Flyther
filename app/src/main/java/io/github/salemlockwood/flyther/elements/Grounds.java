package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.github.salemlockwood.flyther.utils.Screen;

public class Grounds {

    private int GROUNDS_AMOUNT = 3;
    private int GROUND_WIDTH = 800;

    private List<Ground> grounds = new ArrayList<>();
    private Screen screen;
    private Context context;
    private final int weather;

    public Grounds(Screen screen, Context context, int weather){
        this.screen = screen;
        this.context = context;
        this.weather = weather;
        int start_position = 0;
        for(int i=0;i<GROUNDS_AMOUNT;i++){
            grounds.add(new Ground(screen,context,start_position,weather));
            start_position += GROUND_WIDTH;
        }
    }
    public void drawAt(Canvas canvas){
        for(Ground g : grounds){
            g.drawAt(canvas);
        }
    }
    public void move(){
        ListIterator<Ground> iterator = grounds.listIterator();
        while(iterator.hasNext()){
            Ground g = (Ground) iterator.next();
            g.move();
            if(g.offScreen()){
                iterator.remove();
                iterator.add(new Ground(screen,context,getMax()+GROUND_WIDTH,weather));
            }
        }
    }
    public int getMax(){
        int max = 0;
        for(Ground g : grounds){
            max = (Math.max(max,g.getPosition()));
        }
        return max;
    }
}
