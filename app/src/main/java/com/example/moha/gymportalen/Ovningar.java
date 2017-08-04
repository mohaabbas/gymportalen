package com.example.moha.gymportalen;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


/* --------------------------------- ÖVNINGAR ----------------------------------- */


public class Ovningar extends AppCompatActivity {


    //Iconer för övning Item
    int[] ovnPic = {
            R.drawable.abs, R.drawable.abs,
            R.drawable.arms, R.drawable.arms, R.drawable.arms,
            R.drawable.back, R.drawable.back, R.drawable.back,
            R.drawable.chest, R.drawable.chest,
            R.drawable.legs, R.drawable.legs
    };

    //Rubrik för övning Item
    String[] ovnTitle = {
            "Crunch maskin", "Kabelcrunches",
            "Skivstångscurls", "Tricepspushdown", "Liggande tricepspress",
            "Skivstångsrodd", "Latsdrag", "Sittande kabelrodd",
            "Armhävningar", "Bänkpress",
            "Benpress", "Benspark"
    };

    //Kategori för övning Item
    String[] ovnCat = {
            "Mage", "Mage",
            "Armar", "Armar", "Armar",
            "Rygg", "Rygg", "Rygg",
            "Bröst", "Bröst",
            "Ben", "Ben"
    };

    //Texten under AlertDialog
    String[] ovnText = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra et libero non consectetur. Praesent bibendum bibendum augue, et blandit dui sodales sit amet."
    };

    //Övning Item rating
    int[] ovnRating = {
            4, 3,
            2, 5, 5,
            2, 1, 4,
            4, 5,
            1, 3
    };

    //Bilderna under AlertDialog
    int[] ovnImage = {
            R.drawable.cruovning, R.drawable.cablecrun,
            R.drawable.cruovning, R.drawable.cruovning, R.drawable.cruovning,
            R.drawable.cruovning, R.drawable.cruovning, R.drawable.cruovning,
            R.drawable.cruovning, R.drawable.cruovning,
            R.drawable.benpress, R.drawable.cruovning
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovningar);

        //Adapter från min min OvningarAdapter
        ListView ovningList = (ListView) findViewById(R.id.theOvningarList);
        OvningarAdapter myOvningar = new OvningarAdapter(this, ovnPic, ovnTitle, ovnCat, ovnText, ovnImage, ovnRating);
        ovningList.setAdapter(myOvningar);

        //När man har klickar på övningItem
        ovningList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AlertDialog.Builder checkDia = new AlertDialog.Builder(Ovningar.this);
                View mView = getLayoutInflater().inflate(R.layout.ovningar_layout, null);

                TextView ovningLayoutTitle = (TextView) mView.findViewById(R.id.ovningLayoutTitle);
                TextView ovningLayoutText = (TextView) mView.findViewById(R.id.ovningLayoutText);
                ImageView ovningLayoutImage = (ImageView) mView.findViewById(R.id.ovningLayoutImage);
                RatingBar ovningLayoutRating = (RatingBar) mView.findViewById(R.id.ovningLayoutRating);

                //Sätter följande i DialogAlerten
                ovningLayoutTitle.setText(ovnTitle[position]);
                ovningLayoutText.setText(ovnText[position]);
                ovningLayoutImage.setImageResource(ovnImage[position]);
                ovningLayoutRating.setRating(ovnRating[position]);

                //Stänger AlertDialogen
                checkDia.setNegativeButton("Stäng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                    }
                });

                checkDia.setView(mView);
                AlertDialog dia = checkDia.create();
                dia.show();


            }
        });

    }
}
