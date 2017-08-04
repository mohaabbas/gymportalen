package com.example.moha.gymportalen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


/* --------------------------------- MAINACTIVITY ------------------------------------ */


public class MainActivity extends AppCompatActivity {


    ImageView btnAnteckningar, btnOvningar, btnBmi, btnStoppur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnteckningar = (ImageView) findViewById(R.id.btn_anteckningar);
        btnOvningar = (ImageView) findViewById(R.id.btn_ovningar);
        btnBmi = (ImageView) findViewById(R.id.btn_bmi);
        btnStoppur = (ImageView) findViewById(R.id.btn_stoppur);

    }


    //Anteckningar activity
    public void displayAnteckningar(View view){

        startActivity(new Intent(this, Anteckningar.class));

    }

    //Bmi activity
    public void displayBmi(View view){

        startActivity(new Intent(this, Bmi.class));

    }

    //Stoppur activity
    public void displayStoppur(View view){

        startActivity(new Intent(this, Stoppur.class));

    }

    //Övningar activity
    public void displayOvningar(View view){

        startActivity(new Intent(this, Ovningar.class));

    }
}

