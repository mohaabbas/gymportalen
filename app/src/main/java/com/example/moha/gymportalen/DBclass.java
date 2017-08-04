package com.example.moha.gymportalen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/* ------------------------------- DATABASEN ---------------------------------- */


public class DBclass extends SQLiteOpenHelper {


    //Namn och version av databas.
    public static final String dataBaseName = "AntDB";
    public static final int dataBaseVersion = 1;

    //Tabell och columner för Anteckningar
    public static final String antTable = "antTable";
    public static final String antID = "ID";
    public static final String antName = "NAMECOL";
    public static final String antText = "TEXTCOL";
    public static final String antDate = "DATECOL";
    public static final String antRating = "RATINGCOL";


    //Dropar tabell
    private static final String dropTableAnt = "DROP TABLE IF EXIST " + antTable;



    public DBclass(Context context) {
        super(context, dataBaseName, null, dataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Skapar databasen
        db.execSQL("CREATE TABLE " + antTable + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +antName+" TEXT NOT NULL,"
                +antText+" TEXT NOT NULL," +
                "DATECOL DATE," +
                "RATINGCOL REAL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(dropTableAnt);

        onCreate(db);

    }


    //Metoden där man anger värden som skall in i databasen
    public boolean insertData(String antecName, String antecText, float antecRating) {

        //Formatet för datumet
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(antName, antecName);
        contentValues.put(antText, antecText);
        contentValues.put(antDate, dateFormat.format(new Date()));
        contentValues.put(antRating, antecRating);

        long result = db.insert(antTable, null, contentValues);

        return result != -1;

    }


    //Visar information i databasen
    public Cursor viewData(SQLiteDatabase db) {

        String[] minS = new String[]{"_id", antName, antText, antDate, antRating};

        String orderUp = "_id" + " DESC"; //Sorterar så senaste hamnar längst upp

        return db.query(antTable, minS, null, null, null, null, orderUp);

    }


    //Tar bort tabellen genom namnet
    public void deleteData(String name)throws SQLException {
        SQLiteDatabase db = getWritableDatabase();

        if (db == null) {
            return;
        }

        String[] theArgs = new String[] { name };
        db.delete(antTable, antName + "=?", theArgs);
        db.close();
    }


}
