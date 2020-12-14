package com.example.breakout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    public static int index =0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        textView = (EditText) findViewById(R.id.score);
        textView.setActivated(false);
        textView.setEnabled(false);
        textView.setClickable(false);
        for(int i =0;i<index;i++) {
            int a = MainActivity.pref.getInt(String.valueOf(i), MODE_APPEND);
            textView.append(MainActivity.playerName+": "+a+"\n");
        }
    }
    private EditText textView;
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        textView.setText("");
        finish();
    }
}
