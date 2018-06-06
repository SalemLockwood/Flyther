package io.github.salemlockwood.flyther.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Colors {

    public static Paint getScoreColor(Context context) {
        Paint white = new Paint();
        white.setTextSize(80);
        white.setColor(Color.WHITE);
        white.setTypeface(Typeface.DEFAULT_BOLD);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/kenvector_future.ttf");
        white.setTypeface(tf);
        white.setShadowLayer(3, 5, 5, Color.BLACK);
        return white;
    }

    public static Paint getGuiColor(Context context){
        Paint guipaint = new Paint();
        guipaint.setTextSize(160);
        guipaint.setColor(Color.BLACK);
        guipaint.setStyle(Paint.Style.STROKE);
        guipaint.setStrokeWidth(5);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/kenvector_future.ttf");
        guipaint.setTypeface(tf);
        return guipaint;
    }

    public static Paint getGuiDisabledColor(Context context){
        Paint guipaint = new Paint();
        guipaint.setTextSize(160);
        guipaint.setColor(Color.GRAY);
        guipaint.setStyle(Paint.Style.STROKE);
        guipaint.setStrokeWidth(5);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/kenvector_future.ttf");
        guipaint.setTypeface(tf);
        return guipaint;
    }

    public static Paint getLogoColor(Context context){
        Paint paint = new Paint();
        paint.setTextSize(260);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/kenvector_future.ttf");
        paint.setTypeface(tf);
        paint.setShadowLayer(3, 5, 5, Color.BLACK);
        return paint;
    }

    public static Paint getBetaColor(Context context) {
        Paint betaColor = new Paint();
        betaColor.setTextSize(80);
        betaColor.setColor(Color.RED);
        betaColor.setTypeface(Typeface.DEFAULT_BOLD);
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/kenvector_future.ttf");
        betaColor.setTypeface(tf);
        betaColor.setShadowLayer(3, 5, 5, Color.BLACK);
        return betaColor;
    }

    public static Paint getGreenPoint() {
        Paint greenPoint = new Paint();
        greenPoint.setColor(Color.GREEN);
        greenPoint.setStyle(Paint.Style.FILL_AND_STROKE);
        greenPoint.setStrokeWidth(5);
        return greenPoint;
    }

    public static Paint getRedPoint() {
        Paint redPoint = new Paint();
        redPoint.setColor(Color.RED);
        redPoint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPoint.setStrokeWidth(5);
        return redPoint;
    }
}
