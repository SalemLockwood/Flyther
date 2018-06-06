package io.github.salemlockwood.flyther.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import io.github.salemlockwood.flyther.utils.Colors;

public class Score {
    private final Context context;
    private int score = 0;
    
    public Score(Context context){
        this.context = context;
    }

    public void drawAt(Canvas canvas){
        canvas.drawText(String.valueOf(score),100,100, Colors.getScoreColor(context));
    }

    public void addScore() {
        this.score++;
    }
}
