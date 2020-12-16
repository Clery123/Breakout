package com.example.breakout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity {
    private BreakoutView breakout;
    private Random rand;
    private Brick brick;
    private Ball ball;
    private MediaPlayer dingSound;
    public static SharedPreferences.Editor editor;
    private RectF[] rectBric = new RectF[Brick.posLen];
    public static int gameSpeed = 30;
    boolean checker = false;
    private Handler handler = new Handler();
    private AudioManager audioManager;
    double coef = 1.2;
    public static int period =15;
    private int bricLen;
    private boolean back =false;
    private Timer timer;

    private SoundPool soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    private int wall;
    private int paddle;
    private int ding;
    int win;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ball = new Ball(BreakoutView.width,BreakoutView.height);
        BreakoutView.score =0;
        editor = MainActivity.pref.edit();
        wall = soundPool.load(this,R.raw.wall,1);
        paddle = soundPool.load(this,R.raw.paddle,1);
        ding = soundPool.load(this,R.raw.ding,1);
        win = soundPool.load(this,R.raw.win,1);


        this.context = this;
        //   editor.putInt("Player", 10);

        start();

    }
    private void ReverseX(){
        int rand_int2 = rand.nextInt(3)+3;
        if(ball.xVel>0)
            ball.xVel =-rand_int2;
        else
            ball.xVel = rand_int2;
        System.out.println(ball.xVel);
        System.out.println(ball.yVel);

    }
    public void start(){
        back = false;
        breakout = new BreakoutView(this);
        rectBric = breakout.getBric();
        bricLen = Brick.posLen;
        setContentView(breakout);

        rand = new Random();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        breakout.invalidate();
                        ball.update();
                        if(ball.getRect().right >=BreakoutView.width){
                            if(!MainActivity.mt) {
                                soundPool.play(wall, 1.0f, 1.0f, 1, 0, 1.0f);
                            }
                            int rand_int2 = rand.nextInt(3)+3;
                            ball.xVel = -rand_int2;
                        }
                        if(ball.getRect().left <=0){
                            if(!MainActivity.mt) {
                                soundPool.play(wall, 1.0f, 1.0f, 1, 0, 1.0f);
                            }
                            int rand_int2 = rand.nextInt(3)+3;
                            ball.xVel = rand_int2;
                        }
                        if(ball.getRect().top <=0){
                            if(!MainActivity.mt) {
                                soundPool.play(wall, 1.0f, 1.0f, 1, 0, 1.0f);
                            }
                            ball.yVel = -ball.yVel;
                        }
                        if(ball.getRect().bottom>=BreakoutView.height){
                            onBackPressed();
                        }
                        if(RectF.intersects(breakout.getRect(),ball.getRect())){
                            if(!MainActivity.mt) {
                                soundPool.play(paddle, 1.0f, 1.0f, 1, 0, 1.0f);
                            }
                            if(ball.getRect().right >= breakout.getRect().left &&ball.getRect().right <= breakout.getRect().left+200){
                                if(ball.xVel >0)
                                    ReverseX();
                                //ball.xVel = -ball.xVel;
                                ball.yVel = -3;
                            }
                            if(ball.getRect().left >= breakout.getRect().right-200 &&ball.getRect().left <= breakout.getRect().right){
                                if(ball.xVel <0)
                                    ReverseX();
                                //ball.xVel = -ball.xVel;
                                ball.yVel = -3;
                            }
                        }
                        for(int i =0;i<bricLen;i++){
                            if(RectF.intersects(ball.getRect(),rectBric[i])){
                                if(ball.getRect().right < rectBric[i].left+10 || ball.getRect().left > rectBric[i].right-10){
                                    System.out.println("X inverted");
                                    ball.xVel = -ball.xVel;
                                    breakout.invisible=i;
                                    rectBric[i] = rectBric[bricLen-1];
                                    bricLen-=1;
                                }
                                else{
                                    System.out.println("Y inverted");
                                    ball.yVel = -ball.yVel;
                                    breakout.invisible=i;
                                    rectBric[i] = rectBric[bricLen-1];
                                    bricLen-=1;

                                }
                            }
                        }
                        if(bricLen ==0 ){
                            onBackPressed();

                        }

                    }
                });

            }
        },0,period);
    }
    public void onBackPressed(){
        timer.cancel();
        editor.putString(String.valueOf(Score.index), MainActivity.playerName+": "+BreakoutView.score*10);

        Score.index++;
        editor.putInt("index",Score.index);
        editor.commit();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}