package com.example.clockappliction.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.clockappliction.Information.Clock;
import com.example.clockappliction.Information.Student;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=2;

    private static final String DATABASE_NAME="crud.db";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_USER="CREATE TABLE IF NOT EXISTS " + Student.TABLE +"("
                +Student.KEYWORD+" INTEGER PRIMARY KEY AUTOINCREMENT ,"

                +Student.ID+" TEXT ,"
                +Student.NAME+" TEXT ,"
                +Student.GRADE+" TEXT ,"
                +Student.PASSWORD+" TEXT ,"
                +Student.PHONE+" TEXT)";

        db.execSQL(CREATE_TABLE_USER);

        String CREATE_TABLE_CLOCK="CREATE TABLE IF NOT EXISTS " + Clock.TABLE +"("
                +Clock.KEYWORD+" INTEGER PRIMARY KEY AUTOINCREMENT ,"

                +Clock.CID+" TEXT ," //CID==ID

                +Clock.DATE+" TEXT ,"
                +Clock.WORD+" TEXT ,"
                +Clock.SUMMARY+" TEXT ,"
                +Clock.KEEP+" TEXT ,"
                +Clock.MAXDAY+" TEXT)";

        db.execSQL(CREATE_TABLE_CLOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Student.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ Clock.TABLE);
        onCreate(db);
    }
}
