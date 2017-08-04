package com.example.moha.gymportalen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/*---------------------------------- BMI -------------------------------------- */


public class Bmi extends AppCompatActivity {


    //Variabler
    EditText angeLangd, angeVikt;
    TextView resultatSiffra, halsaView;
    double nr1, nr2, resultat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        resultatSiffra = (TextView) findViewById(R.id.resultatSiffra);
        halsaView = (TextView) findViewById(R.id.halsanView);

        angeLangd = (EditText) findViewById(R.id.ange_langd);
        angeVikt = (EditText) findViewById(R.id.ange_vikt);


        Button visaDialog = (Button) findViewById(R.id.btnBeraknaBmi);
        visaDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nr1 = Double.parseDouble(angeLangd.getText().toString());
                nr2 = Double.parseDouble(angeVikt.getText().toString());

                nr1 = nr1/100;
                nr1 = nr1*nr1;

                resultat = nr2 / nr1;
                resultatSiffra.setText("" + resultat);

                if(resultat < 19){
                    halsaView.setText("Du är underviktig");
                } else if(resultat == 19 || resultat <= 26){
                    halsaView.setText("Du har idealvikt");
                } else if(resultat == 26 || resultat < 30){
                    halsaView.setText("Du är överviktig");
                } else if(resultat > 30){
                    halsaView.setText("Varning för fetma!");
                } else {
                    halsaView.setText("HÄLSA?");
                }


            }
        });

    }
}
