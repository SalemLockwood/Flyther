package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import io.github.salemlockwood.flyther.R;
import io.github.salemlockwood.flyther.utils.Colors;
import io.github.salemlockwood.flyther.utils.Screen;

public class GameOver {

    private final Screen screen;
    private Context context;

    public GameOver(Screen screen,Context context){
        this.screen = screen;
        this.context = context;
    }

    public void drawAt(Canvas canvas){
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),R.drawable.textgameover);
        Bitmap gobp = bp.createScaledBitmap(bp,600,200, false);
        canvas.drawBitmap(gobp,screen.getWidth()/2-300,screen.getHeight()/2-100,null);
    }
}
