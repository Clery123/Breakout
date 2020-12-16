package com.example.breakout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonLevels;
    private Button muteSound;
    private EditText editText;
    private Button buttonScore;
    public static String playerName ="Player";
    public static SharedPreferences pref;
    public static boolean mt;
    Context context;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref  = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        setContentView(R.layout.menu);
        editText = (EditText)findViewById(R.id.pname);
        editText.setText(playerName);
        context = this;
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        mt = sp.getBoolean("muted",false);
        System.out.println(mt);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                playerName = editText.getText().toString();
                System.out.println(playerName);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonPlay = findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLevels = findViewById(R.id.levels);
        buttonLevels.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
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
        muteSound = findViewById(R.id.mute);
        muteSound.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
                mt = sp.getBoolean("muted",false);
                if(mt){
                    muteSound.setText("Mute Sounds");
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putBoolean("muted",false);
                    edit.apply();
                    edit.commit();
                    mt = sp.getBoolean("muted",false);
                    System.out.println(mt);
                }
                else if(!mt){
                    muteSound.setText("Unmute Sounds");
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putBoolean("muted",true);
                    edit.apply();
                    edit.commit();
                    mt = sp.getBoolean("muted",false);
                    System.out.println(mt);
                }

            }
        });
        if(mt){
            muteSound.setText("Unmute Sounds");}
        else if(!mt)
            muteSound.setText("Mute Sounds");
    }


}