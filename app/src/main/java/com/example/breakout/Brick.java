package com.example.breakout;

import android.graphics.RectF;

public class Brick {
    public static float[][] pos = new float[][]{
        {(float) (BreakoutView.width * 0.10), (float) (BreakoutView.height * 0.10)},
        {(float) (BreakoutView.width * 0.35), (float) (BreakoutView.height * 0.10)},
        {(float) (BreakoutView.width * 0.60), (float) (BreakoutView.height * 0.10)},
        {(float) (BreakoutView.width * 0.10), (float) (BreakoutView.height * 0.15)},
        {(float) (BreakoutView.width * 0.35), (float) (BreakoutView.height * 0.15)},
        {(float) (BreakoutView.width * 0.60), (float) (BreakoutView.height * 0.15)}
    };
    public static int posLen =pos.length;
    public static RectF setThis(int i){
        RectF rect = new RectF();
        rect.left=pos[i][0];
        rect.top =pos[i][1];
        rect.right =pos[i][0]+200;
        rect.bottom =pos[i][1]+50;
        return rect;
    }
}
