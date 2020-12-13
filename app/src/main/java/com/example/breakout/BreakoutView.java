package com.example.breakout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class BreakoutView extends View{
    public static int score =0;
    private RectF rect;
    //Display Size
    DisplayMetrics displayMetrics = new DisplayMetrics();
    public static int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int height= Resources.getSystem().getDisplayMetrics().heightPixels;
    //Paint
    Paint tbPaint = new Paint();
    Paint toPaint = new Paint();
    Paint tb2Paint = new Paint();
    Paint tgPaint = new Paint();
    //Cursor Position
    float cursorX =0;
    // if on touch
    boolean touch =false;
    int value=Brick.posLen;
    private RectF[] bric = new RectF[Brick.posLen];
    //public StringBuilder invisible = new StringBuilder();
    public static int invisible=-1;
    //Game Board


    //////////////////
    public BreakoutView(Context context) {
        super(context);
        init();
    }
    public RectF[] getBric(){
        return this.bric;
    }
    public BreakoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        value=Brick.posLen;
        for(int i =0;i<value;i++) {
            bric[i] = Brick.setThis(i);
        }
        toPaint.setStrokeWidth(35);
        toPaint.setColor(Color.rgb(204, 118, 0));
        tbPaint.setStrokeWidth(35);
        tb2Paint.setStrokeWidth(60);
        tbPaint.setTextSize(38);
        tbPaint.setColor(Color.BLACK);
        tb2Paint.setColor(Color.BLACK);
        tgPaint.setColor(Color.GRAY);
        rect = new RectF(cursorX,height*(float)0.85,cursorX+400,height*(float)0.85+20);

    }
    public RectF getRect(){
        return rect;
    }
    public BreakoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getX() >= width-200)
            cursorX = width-400;
        else if(event.getX()<=200)
            cursorX = 0;
        else
            cursorX = event.getX()-200;
        touch = true;
        return true;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        canvas.drawRect(Ball.getRect(),tgPaint);
        for(int i=0;i<value;i++) {
            try{
                if(invisible==i) {
                    bric[i] = bric[value - 1];
                    value -= 1;
                    invisible=-1;
                    score++;
                }
                canvas.drawRect(bric[i].left, bric[i].top, bric[i].right, bric[i].bottom, toPaint);
                System.out.println(value);
            }
            catch (Exception e){
                System.out.println("cc");
            }

        }

        canvas.drawText("Score: "+score*10,10,50,tbPaint);
        canvas.drawRect(getRect(),tbPaint);
        if(value==0){
            Paint tBig = new Paint();
            tBig.setTextSize(100);
            tBig.setColor(Color.BLACK);
            invalidate();
            canvas.drawText("Score: "+score*10,width/3,height/2,tBig);
        }
        rect.left = cursorX;
        rect.top = height*(float)0.85;
        rect.right = cursorX+400;
        rect.bottom = height*(float)0.85+20;

    }
}
