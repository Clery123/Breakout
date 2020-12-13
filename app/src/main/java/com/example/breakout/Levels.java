package com.example.breakout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Levels extends AppCompatActivity {
    private SeekBar seekBar;
    private Button level1;
    private TextView textView;
    private Button level2;
    private Button level3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        textView = (TextView)findViewById(R.id.gameSpeed);
        textView.setText("Game Speed: 3");
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Game.period = seekBar.getProgress();
                textView.setText("Game Speed: "+seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        level1 = findViewById(R.id.level1);
        level1.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              Brick.pos = new float[][]{
                      {(float) (BreakoutView.width * 0.10), (float) (BreakoutView.height * 0.10)},
                      {(float) (BreakoutView.width * 0.35), (float) (BreakoutView.height * 0.10)},
                      {(float) (BreakoutView.width * 0.60), (float) (BreakoutView.height * 0.10)},
                      {(float) (BreakoutView.width * 0.10), (float) (BreakoutView.height * 0.15)},
                      {(float) (BreakoutView.width * 0.35), (float) (BreakoutView.height * 0.15)},
                      {(float) (BreakoutView.width * 0.60), (float) (BreakoutView.height * 0.15)}
              };
              Game.period=15;
              seekBar.setProgress(15);
              Brick.posLen=6;
          }
        });


            level2 = findViewById(R.id.level2);
        level2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Brick.pos = new float[][]{
                        {(float) (BreakoutView.width * 0.10), (float) (BreakoutView.height * 0.10)}, /* 1*/{(float) (BreakoutView.width * 0.10), (float) (BreakoutView.height * 0.15)},/*2*/
                        {(float) (BreakoutView.width * 0.60), (float) (BreakoutView.height * 0.10)},/*3*/ {(float) (BreakoutView.width * 0.60), (float) (BreakoutView.height * 0.15)}, /*4*/
                        {(float) (BreakoutView.width * 0.35), (float) (BreakoutView.height * 0.15)},/*5*/ {(float) (BreakoutView.width * 0.35), (float) (BreakoutView.height * 0.10)}, /*6*/
                        {(float) (BreakoutView.width * 0.50), (float) (BreakoutView.height * 0.25)},/*5*/ {(float) (BreakoutView.width * 0.25), (float) (BreakoutView.height * 0.25)} /*6*/
                };
                //onBackPressed();
                Game.period = 7;
                seekBar.setProgress(7);
                Brick.posLen = 8;
            }
        });
        level3 = findViewById(R.id.level3);
        level3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                float scale = 20000/BreakoutView.width;
                float scaleh = 10000/BreakoutView.height;
                Brick.pos = new float[][]{
                        {(float) (BreakoutView.width * 0)+(float)(BreakoutView.width*0.1),                (float) (BreakoutView.height * scaleh/100)},
                        {(float) (BreakoutView.width * scale/100)+(float)(BreakoutView.width*0.1)+10,     (float) (BreakoutView.height * scaleh/100)},
                        {(float) (BreakoutView.width * (scale/100)*2)+(float)(BreakoutView.width*0.1)+20, (float) (BreakoutView.height * scaleh/100)},
                        {(float) (BreakoutView.width * (scale/100)*3)+(float)(BreakoutView.width*0.1)+30, (float) (BreakoutView.height * scaleh/100)},

                        {(float) (BreakoutView.width * 0)+(float)(BreakoutView.width*0.1),                (float) (BreakoutView.height * scaleh/100)+52},
                        {(float) (BreakoutView.width * scale/100)+(float)(BreakoutView.width*0.1)+10,     (float) (BreakoutView.height * scaleh/100)+52},
                        {(float) (BreakoutView.width * (scale/100)*2)+(float)(BreakoutView.width*0.1)+20, (float) (BreakoutView.height * scaleh/100)+52},
                        {(float) (BreakoutView.width * (scale/100)*3)+(float)(BreakoutView.width*0.1)+30, (float) (BreakoutView.height * scaleh/100)+52},

                        {(float) (BreakoutView.width * 0)+(float)(BreakoutView.width*0.1),                (float) (BreakoutView.height * scaleh/100)+105},
                        {(float) (BreakoutView.width * scale/100)+(float)(BreakoutView.width*0.1)+10,     (float) (BreakoutView.height * scaleh/100)+105},
                        {(float) (BreakoutView.width * (scale/100)*2)+(float)(BreakoutView.width*0.1)+20, (float) (BreakoutView.height * scaleh/100)+105},
                        {(float) (BreakoutView.width * (scale/100)*3)+(float)(BreakoutView.width*0.1)+30, (float) (BreakoutView.height * scaleh/100)+105},

                        {(float) (BreakoutView.width * 0)+(float)(BreakoutView.width*0.1),                (float) (BreakoutView.height * scaleh/100)+158},
                        {(float) (BreakoutView.width * scale/100)+(float)(BreakoutView.width*0.1)+10,     (float) (BreakoutView.height * scaleh/100)+158},
                        {(float) (BreakoutView.width * (scale/100)*2)+(float)(BreakoutView.width*0.1)+20, (float) (BreakoutView.height * scaleh/100)+158},
                        {(float) (BreakoutView.width * (scale/100)*3)+(float)(BreakoutView.width*0.1)+30, (float) (BreakoutView.height * scaleh/100)+158}
                };
                //onBackPressed();
                Game.period = 7;
                seekBar.setProgress(7);
                Brick.posLen = 16;
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
