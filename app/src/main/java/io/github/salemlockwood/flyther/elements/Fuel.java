package io.github.salemlockwood.flyther.elements;

import android.graphics.Canvas;
import android.graphics.Rect;

import io.github.salemlockwood.flyther.utils.Colors;
import io.github.salemlockwood.flyther.utils.Screen;

public class Fuel {

    private static final int TOTAL_FUEL = 1800;

    private int fuel = TOTAL_FUEL;
    private Screen screen;

    public Fuel(Screen screen){
        this.screen = screen;
    }
    public void drawAt(Canvas canvas){
        Rect fuelBar = new Rect(50,screen.getHeight()-100,fuel/2 + 50,screen.getHeight()-50);
        Rect fuelBarStoke = new Rect(50,screen.getHeight()-100,TOTAL_FUEL/2 + 50,screen.getHeight()-50);
        canvas.drawRect(fuelBar, Colors.getFuelBarPaint());
        canvas.drawRect(fuelBarStoke, Colors.getFuelBarStrokePaint());
        fuel--;
    }
    public void dropFuel(){
        fuel -= 30;
    }
    public void fuelSupply(int fuel){
        this.fuel += fuel > TOTAL_FUEL ? TOTAL_FUEL : fuel;
    }

    public int getFuel() {
        return fuel;
    }
}
