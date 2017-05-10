package com.example.dks.expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dks on 10/5/17.
 */

public class Database extends SQLiteOpenHelper {


    private static final String _DBName = "local_database";
    private static final String _MainAccount = "main_account_table";
    private static final String _MainPeople = "main_people_table";
    private static final String _SrNo = "serial_no";

    // attributes for account table
    private static final String _AccName = "account_name";
    private static final String _AccUserName = "account_username";
    private static final String _AccPassword = "account_password";
    private static final String _AccCode = "account_code";

    //attributes for people table
    private static final String _PName = "people_name";
    private static final String _PTotal= "people_total";
    private static final String _PDate = "particular_date";
    private static final String _PAmount = "particular_amount";
    private static final String _Comment = "comment";

    public Database(Context context) {
        super(context, _DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+_MainAccount+" (`"+_SrNo+"` INTEGER PRIMARY KEY AUTOINCREMENT, `"+_AccName+"` TEXT, `"+_AccUserName+"` TEXT, `"+_AccPassword+"` TEXT, `"+_Comment+"` TEXT, `"+_AccCode+"` INTEGER)");
        db.execSQL("create table "+_MainPeople+" (`"+_SrNo+"` INTEGER PRIMARY KEY AUTOINCREMENT, `"+_PName+"` TEXT, `"+_PDate+"` DATE DEFAULT CURRENT_DATE, `"+_PTotal+"` INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPeople(String Pname){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table "+Pname+" (`"+_SrNo+"` INTEGER PRIMARY KEY AUTOINCREMENT, `"+_PTotal+"` INTEGER, `"+_PAmount+"` INTEGER, `"+_PDate+"` DATE DEFAULT CURRENT_DATE, `"+_Comment+"` TEXT)");
    }

    public void updateBalance(String PName,int Amount,String Comment){
        SQLiteDatabase db = this.getWritableDatabase();
        int Total = getTotal(PName);
        Total = Total + Amount;
        ContentValues values = new ContentValues();
        values.put(_PAmount,Amount);
        values.put(_Comment,Comment);
        values.put(_PTotal,Total);
        db.insert(PName,null,values);

    }

    public int getTotal(String Pname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select `"+_PTotal+"` from `"+Pname+"` ",null);
        if (cursor == null || cursor.getCount() == 0){
            return 0;
        }
        cursor.moveToLast();
        return cursor.getInt(0);
    }

    public Cursor getPeople(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select `"+_PName+"`, `"+_PTotal+"` from `"+_MainPeople+"` ",null);
        return cursor;
    }



}
