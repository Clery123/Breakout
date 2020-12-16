package com.example.breakout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    public static int index =0;
    private EditText textView;
    private Button delete;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        index = MainActivity.pref.getInt("index",0);
        System.out.println(index);
        textView = (EditText) findViewById(R.id.score);
        textView.setActivated(false);
        textView.setEnabled(false);
        textView.setClickable(false);
        for(int i =0;i<index;i++) {
            String a = MainActivity.pref.getString(String.valueOf(i), "");
            System.out.println(a);
            textView.append(MainActivity.pref.getString(a,a+"\n"));
            //textView.setText(MainActivity.playerName+": "+a+"\n");
        }

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i<=index;i++){
                    MainActivity.pref.edit().remove(String.valueOf(index)).commit();
                }
                MainActivity.pref.edit().remove("index").commit();
                textView.setText("");
                index = -1;
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        textView.setText("");
        finish();
    }
}