package com.example.moha.gymportalen;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;


/* ------------------------------ STOPPUR ------------------------------ */


public class Stoppur extends AppCompatActivity {


    private Chronometer chronoView;
    private ImageView startBtn;
    private ImageView pauseBtn;
    private ImageView resetBtn;
    private long pauseHolder = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoppur);


        chronoView = (Chronometer) findViewById(R.id.chronoView);
        startBtn = (ImageView) findViewById(R.id.btnStart);
        pauseBtn = (ImageView) findViewById(R.id.btnPause);
        resetBtn = (ImageView) findViewById(R.id.btnReset);

        //Start knappen
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronoView.setBase(SystemClock.elapsedRealtime()+ pauseHolder);
                chronoView.start();

            }
        });

        //Paus knappen
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseHolder = chronoView.getBase() - SystemClock.elapsedRealtime();
                chronoView.stop();

            }
        });

        //Reset knappen
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronoView.setBase(SystemClock.elapsedRealtime());
                chronoView.stop();
                pauseHolder = 0;

            }
        });



    }

}
