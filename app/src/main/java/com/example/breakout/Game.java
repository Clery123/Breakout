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
    private MediaPlayer paddle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ball = new Ball(BreakoutView.width,BreakoutView.height);
        BreakoutView.score =0;
        audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        editor = MainActivity.pref.edit();
        dingSound = MediaPlayer.create(this,R.raw.ding);
        paddle = MediaPlayer.create(this,R.raw.paddle);
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
                        //Colision();
                        ball.update();
                        if(ball.getRect().right >=BreakoutView.width){
                            int rand_int2 = rand.nextInt(3)+3;
                            ball.xVel = -rand_int2;
                        }
                        if(ball.getRect().left <=0){
                            int rand_int2 = rand.nextInt(3)+3;
                            ball.xVel = rand_int2;
                        }
                        if(ball.getRect().top <=0){
                            ball.yVel = -ball.yVel;
                        }
                        if(RectF.intersects(breakout.getRect(),ball.getRect())){
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                            paddle.start();
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
                                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                                dingSound.start();
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
        editor.putInt(String.valueOf(Score.index), BreakoutView.score*10);
        Score.index++;
        editor.commit();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
