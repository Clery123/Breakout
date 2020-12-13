package com.example.breakout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonLevels;
    private Button buttonScore;
    public static SharedPreferences pref;

    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref  = getApplicationContext().getSharedPreferences("MyPref", 0);
        setContentView(R.layout.menu);
        buttonPlay = findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("kek");
                Intent intent = new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLevels = findViewById(R.id.levels);
        buttonLevels.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("kekovička");
                Intent intent = new Intent(getApplicationContext(),Levels.class);
                startActivity(intent);
                finish();
            }
        });
        buttonScore = findViewById(R.id.score);
        buttonScore.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Score.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /*public void Colision(){

        if(breakout.ball_PosY<breakout.height*(float)0.45){
            for(int i = 0;i<breakout.posLen;i++){
                if((breakout.ball_PosX >= breakout.pos[i][0] && breakout.ball_PosX <= breakout.pos[i][0]+220 && breakout.ball_PosY>=breakout.pos[i][1]+30 && breakout.ball_PosY<=breakout.pos[i][1]+65 &&breakout.gravity <0)){
                    if(breakout.bounce <0 && breakout.ball_PosX <= breakout.pos[i][0]+220 && breakout.ball_PosX >= breakout.pos[i][0]+190){
                        breakout.bounce = -breakout.bounce;
                        System.out.println("zboku");
                        float x = breakout.pos[i][0];
                        float y = breakout.pos[i][1];
                        breakout.pos[i][0] = breakout.pos[breakout.posLen - 1][0];
                        breakout.pos[breakout.posLen - 1][0] = x;

                        breakout.pos[i][1] = breakout.pos[breakout.posLen - 1][1];
                        breakout.pos[breakout.posLen - 1][1] = y;
                        breakout.posLen -= 1;
                        break;
                    }
                    else {
                        System.out.println("cheche");
                        System.out.println(breakout.ball_PosX);
                        breakout.gravity = -breakout.gravity;
                        float x = breakout.pos[i][0];
                        float y = breakout.pos[i][1];
                        breakout.pos[i][0] = breakout.pos[breakout.posLen - 1][0];
                        breakout.pos[breakout.posLen - 1][0] = x;

                        breakout.pos[i][1] = breakout.pos[breakout.posLen - 1][1];
                        breakout.pos[breakout.posLen - 1][1] = y;
                        breakout.posLen -= 1;
                        break;
                    }
                }
                else if(breakout.ball_PosX >= breakout.pos[i][0]-30 && breakout.ball_PosX <= breakout.pos[i][0]+220 && breakout.ball_PosY>=breakout.pos[i][1]-45 && breakout.ball_PosY<=breakout.pos[i][1]+25 &&breakout.gravity >0){
                    if(breakout.bounce >0 && breakout.ball_PosX >= breakout.pos[i][0]-35 && breakout.ball_PosX <= breakout.pos[i][0]){
                        breakout.bounce = -breakout.bounce;
                        System.out.println("MLASDM");
                        float x = breakout.pos[i][0];
                        float y = breakout.pos[i][1];
                        breakout.pos[i][0] = breakout.pos[breakout.posLen - 1][0];
                        breakout.pos[breakout.posLen - 1][0] = x;

                        breakout.pos[i][1] = breakout.pos[breakout.posLen - 1][1];
                        breakout.pos[breakout.posLen - 1][1] = y;
                        breakout.posLen -= 1;
                        break;
                    }
                    else {
                        System.out.println("cheche2");
                        System.out.println(breakout.bounce);
                        breakout.gravity = -breakout.gravity;
                        float x = breakout.pos[i][0];
                        float y = breakout.pos[i][1];
                        breakout.pos[i][0] = breakout.pos[breakout.posLen - 1][0];
                        breakout.pos[breakout.posLen - 1][0] = x;

                        breakout.pos[i][1] = breakout.pos[breakout.posLen - 1][1];
                        breakout.pos[breakout.posLen - 1][1] = y;
                        breakout.posLen -= 1;
                        break;
                    }
            }
            }
        }
    }*/
}