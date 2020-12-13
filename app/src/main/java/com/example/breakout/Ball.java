package com.example.breakout;

import android.graphics.RectF;

public class Ball {
    public static RectF rect;
    public static int xVel;
    public static int yVel;
    public int width;
    public int height;
    public Ball(int screenX, int screenY){
        xVel =4;
        yVel =3;
        rect = new RectF();
        rect.left+=150;
        rect.top +=450;
    }
    public static RectF getRect(){
        return rect;
    }
    public static void update(){
        rect.left+=xVel;
        rect.top +=yVel;
        rect.right = rect.left+30;
        rect.bottom = rect.top +30;
    }

}
