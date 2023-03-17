package com.example.clockappliction.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.clockappliction.Information.Clock;
import com.example.clockappliction.Information.Student;

import java.util.ArrayList;

public class CRUD {

    private DBHelper dbHelper;

    public CRUD (Context context){dbHelper = new DBHelper(context);}

    public int insertStudent(Student student){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Student.ID,student.id);
        values.put(Student.NAME,student.name);
        values.put(Student.GRADE,student.grade);
        values.put(Student.PHONE,student.phone);
        values.put(Student.PASSWORD,student.password);

        long keywords = db.insert(Student.TABLE,null,values);
        db.close();
        return (int) keywords;
    }
    public void insertClock(Clock clock){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Clock.DATE,clock.date);
        values.put(Clock.WORD,clock.word);
        values.put(Clock.SUMMARY,clock.summary);
        values.put(Clock.KEEP,clock.keep);
        values.put(Clock.MAXDAY,clock.maxday);

        values.put(Clock.CID,clock.cid);

        db.insert(Clock.TABLE,null,values);
        db.close();
    }
    @SuppressLint("Range")
    public Clock getClockByKeyWord(int key){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Clock.TABLE + " WHERE " + Clock.KEYWORD +"=?";
        Clock clock = new Clock();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(key)});
        if (cursor.moveToFirst()){

                clock.date = cursor.getString(cursor.getColumnIndex(Clock.DATE));
                clock.word = cursor.getString(cursor.getColumnIndex(Clock.WORD));
                clock.summary = cursor.getString(cursor.getColumnIndex(Clock.SUMMARY));
                clock.maxday = cursor.getString(cursor.getColumnIndex(Clock.MAXDAY));
                clock.keep = cursor.getString(cursor.getColumnIndex(Clock.KEEP));
                cursor.close();
                db.close();
                return clock;
        }
        cursor.close();
        db.close();
        return null;
    }
    @SuppressLint("Range")
    public Clock getClockByDate(String the_date){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Clock.TABLE + " WHERE " + Clock.DATE +"=?";
        Clock clock = new Clock();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{the_date});
        if (cursor.moveToFirst()){

            clock.date = cursor.getString(cursor.getColumnIndex(Clock.DATE));
            clock.word = cursor.getString(cursor.getColumnIndex(Clock.WORD));
            clock.summary = cursor.getString(cursor.getColumnIndex(Clock.SUMMARY));
            clock.maxday = cursor.getString(cursor.getColumnIndex(Clock.MAXDAY));
            clock.keep = cursor.getString(cursor.getColumnIndex(Clock.KEEP));
            cursor.close();
            db.close();
            return clock;
        }
        cursor.close();
        db.close();
        return null;
    }
    @SuppressLint("Range")
    public int getCountClock(){
        //查询记录数量
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT (*) FROM "+Clock.TABLE,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
    }
    @SuppressLint("Range")
    public  Student getStudentById(String id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT "+
                Student.KEYWORD +","+
                Student.ID +","+
                Student.NAME +","+
                Student.GRADE + ","+
                Student.PASSWORD + ","+
                Student.PHONE+
                " FROM " + Student.TABLE
                +" WHERE " +
                Student.ID + "=?";
        int count = 0;
        Student student = new Student();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
                student.keyword = cursor.getInt(cursor.getColumnIndex(Student.KEYWORD));
                student.id = cursor.getString(cursor.getColumnIndex(Student.ID));
                student.name = cursor.getString(cursor.getColumnIndex(Student.NAME));
                student.grade = cursor.getString(cursor.getColumnIndex(Student.GRADE));
                student.phone = cursor.getString(cursor.getColumnIndex(Student.PHONE));
                student.password = cursor.getString(cursor.getColumnIndex(Student.PASSWORD));
        }
        cursor.close();
        db.close();
        return student;
    }
    @SuppressLint("Range")
    public Student getStudentByKeyWord(int keyword){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT "+
                Student.KEYWORD +","+
                Student.ID +","+
                Student.NAME +","+
                Student.GRADE + ","+
                Student.PASSWORD + ","+
                Student.PHONE+
                " FROM " + Student.TABLE
                +" WHERE " +
                Student.KEYWORD + "=?";
        int count = 0;
        Student student = new Student();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(keyword)});
        if (cursor.moveToFirst()){
                student.keyword = cursor.getInt(cursor.getColumnIndex(Student.KEYWORD));
                student.id = cursor.getString(cursor.getColumnIndex(Student.ID));
                student.name = cursor.getString(cursor.getColumnIndex(Student.NAME));
                student.grade = cursor.getString(cursor.getColumnIndex(Student.GRADE));
                student.phone = cursor.getString(cursor.getColumnIndex(Student.PHONE));
                student.password = cursor.getString(cursor.getColumnIndex(Student.PASSWORD));
        }
        cursor.close();
        db.close();
        return student;
    }
    @SuppressLint("Range")
    public ArrayList<Clock> getClockByWord(String word){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Clock> list1 = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Clock.TABLE + " WHERE " + Clock.WORD + " LIKE "+"?" ;

        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf("%"+word+"%")});

        if (cursor.moveToFirst()){
            do {
                Clock clock = new Clock();
                clock.date = cursor.getString(cursor.getColumnIndex(Clock.DATE));
                clock.word = cursor.getString(cursor.getColumnIndex(Clock.WORD));
                clock.summary = cursor.getString(cursor.getColumnIndex(Clock.SUMMARY));
                clock.maxday = cursor.getString(cursor.getColumnIndex(Clock.MAXDAY));
                clock.keep = cursor.getString(cursor.getColumnIndex(Clock.KEEP));
                list1.add(clock);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list1;
    }
    @SuppressLint("Range")
    public ArrayList<Clock> getClockBySum(String sum){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Clock> list1 = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Clock.TABLE + " WHERE " + Clock.SUMMARY + " LIKE "+"?" ;


        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf("%"+sum+"%")});

        if (cursor.moveToFirst()){
            do {
                Clock clock = new Clock();
                clock.date = cursor.getString(cursor.getColumnIndex(Clock.DATE));
                clock.word = cursor.getString(cursor.getColumnIndex(Clock.WORD));
                clock.summary = cursor.getString(cursor.getColumnIndex(Clock.SUMMARY));
                clock.maxday = cursor.getString(cursor.getColumnIndex(Clock.MAXDAY));
                clock.keep = cursor.getString(cursor.getColumnIndex(Clock.KEEP));
                list1.add(clock);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list1;
    }

}
