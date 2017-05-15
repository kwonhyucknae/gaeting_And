package com.gaetingdev.m.gaetingdev.Framework.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by soooo on 2016-10-01.
 */

// DB연결을 위한 헬퍼
public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "gaeting_db";
    private final static String TABLE_NAME = "id_table";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, login_type REAL, id TEXT)";
        db.execSQL(createTable);
        db.execSQL("INSERT INTO id_table VALUES (NULL, -1, 'empty');");
    }
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){
    }
}
