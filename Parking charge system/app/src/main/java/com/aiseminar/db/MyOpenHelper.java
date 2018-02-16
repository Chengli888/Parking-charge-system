package com.aiseminar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/9.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "info2.db", null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table info2(_id integer primary key autoincrement,time varchar(30),plate varchar(30),color varchar(20),type varchar(20),money varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table time1(_id integer primary key autoincrement,pm_littercar varchar(20),pm_bigcar varchar(20))");
        db.execSQL("create table time2(_id integer primary key autoincrement,begin_time varchar(20),end_time varchar(20),littercar1 varchar(20),bigcar1 varchar(20),littercar2 varchar(20),bigcar2 varchar(20))");
    }
}
