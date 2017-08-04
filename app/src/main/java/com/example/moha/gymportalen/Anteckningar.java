package com.example.moha.gymportalen;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


/*------------------------------------- ANTECKNINGAR ----------------------------------------*/


public class Anteckningar extends AppCompatActivity {

    //Min databas
    DBclass myDB;

    //Plusset som skapar anteckning.
    ImageView showDial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anteckningar);

        myDB = new DBclass(this);

        showDial = (ImageView) findViewById(R.id.btnAddAnteckningar);
        final ListView myList = (ListView) findViewById(R.id.theListView);

            final DBclass minDB = new DBclass(this);
            final SQLiteDatabase db = minDB.getReadableDatabase();

            //Min CursurAdapter för anteckningar
            Cursor cursor = minDB.viewData(db);

            CursorAdapter curAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, cursor,
                    new String[] {DBclass.antName, DBclass.antDate}, new int[] {R.id.tvName, R.id.tvText}, 0);
            myList.setAdapter(curAdapter);


        //Item-click när man man klickar på anteckningarnas item.
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                //Hämtar positionen
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                //Hämtar string som från följande
                final String checkName = cursor.getString(cursor.getColumnIndexOrThrow("NAMECOL"));
                final String checkText = cursor.getString(cursor.getColumnIndexOrThrow("TEXTCOL"));
                final String checkDate = cursor.getString(cursor.getColumnIndexOrThrow("DATECOL"));

                //AlertDialogen som visas
                AlertDialog.Builder checkDia = new AlertDialog.Builder(Anteckningar.this);
                View mView = getLayoutInflater().inflate(R.layout.check_layout, null);

                final TextView chTitle = (TextView) mView.findViewById(R.id.checkTitle);
                final TextView chText = (TextView) mView.findViewById(R.id.checkText);
                final TextView chDate = (TextView) mView.findViewById(R.id.checkDate);

                //Sätter string där den ska vara
                chTitle.setText(checkName);
                chText.setText(checkText);
                chDate.setText(checkDate);

                //Stänger dialog när användaren klickar på Stäng
                checkDia.setNegativeButton("Stäng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                //Tar bort samt stänger dialog
                checkDia.setNeutralButton("Tabort anteckning", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myDB.deleteData(checkName);
                        onRestart(); //Metoden refreshar listan
                        dialog.dismiss();
                    }
                });

                checkDia.setView(mView);
                AlertDialog dia = checkDia.create();
                dia.show();

            }
        });


        //Skapar anteckningar
        showDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Anteckningar.this);
                View mView = getLayoutInflater().inflate(R.layout.add_anteckningar, null);

                final EditText edName = (EditText) mView.findViewById(R.id.edit_name);
                final EditText edText = (EditText) mView.findViewById(R.id.edit_text);
                final RatingBar edRating = (RatingBar) mView.findViewById(R.id.edit_rating);
                Button sparaAntecInfo = (Button) mView.findViewById(R.id.btnSpara);

                mBuilder.setPositiveButton("Stäng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onRestart();
                        dialog.dismiss();
                    }
                });

                //Sparar infon i databasen
                sparaAntecInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = myDB.insertData(
                                edName.getText().toString(),
                                edText.getText().toString(),
                                edRating.getRating()
                        );

                        if (isInserted) {
                            Toast.makeText(Anteckningar.this, "Anteckning skapad!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Anteckningar.this, "Något gick fel!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dia = mBuilder.create();
                dia.show();

            }
        });

    }


    //Skapar objekt i listan utan att man behöver startar om activityn.
    @Override
    protected void onRestart() {
        super.onRestart();

            DBclass minDb = new DBclass(this);
            SQLiteDatabase db = minDb.getReadableDatabase();

            Cursor cursor = minDb.viewData(db);
            ListView minLista = (ListView) findViewById(R.id.theListView);

            CursorAdapter ca = (CursorAdapter)minLista.getAdapter();
            ca.changeCursor(cursor);

    }

}


