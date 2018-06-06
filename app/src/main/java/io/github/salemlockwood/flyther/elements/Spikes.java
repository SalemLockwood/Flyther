package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.github.salemlockwood.flyther.utils.Screen;

public class Spikes {

    private static final int SPIKES_AMOUNT = 5;
    private static final int SPACE_BETWEEN_SPIKES = 450;

    private List<Spike> spikes = new ArrayList<>();
    private Screen screen;
    private Score score;
    private Context context;
    private final int weather;

    public Spikes(Screen screen, Score score, Context context, int weather){
        this.screen = screen;
        this.score = score;
        int start_position = 200;
        this.context = context;
        this.weather = weather;

        for(int i=0;i<SPIKES_AMOUNT;i++){
            start_position += SPACE_BETWEEN_SPIKES;
            spikes.add(new Spike(screen,start_position,context,weather));
        }
    }

    public void drawAt(Canvas canvas){
        for(Spike spike : spikes){
            spike.drawAt(canvas);
        }
    }

    public void move(){
        ListIterator<Spike> iterator = spikes.listIterator();
        while(iterator.hasNext()){
            Spike spike = (Spike) iterator.next();
            spike.move();
            if(spike.offScreen()){
                score.addScore();
                iterator.remove();
                Spike newSpike = new Spike(screen,getMax() + SPACE_BETWEEN_SPIKES,context,weather);
                iterator.add(newSpike);
            }
        }
    }

    private int getMax(){
        int max = 0;
        for(Spike spike : spikes){
            max = Math.max(spike.getPosition(),max);
        }
        return max;
    }

    public boolean hasCollisionWith(Plane plane) {
        for(Spike spike : spikes){
            if(spike.hasCollisionWith(plane))
                return true;
        }
        return false;
    }
}
