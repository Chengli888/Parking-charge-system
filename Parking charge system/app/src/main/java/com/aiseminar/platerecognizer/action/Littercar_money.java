package com.aiseminar.platerecognizer.action;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aiseminar.db.MyOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/16.
 */

public class Littercar_money {
    public  String getmoney( SimpleDateFormat sdf,MyOpenHelper myOpenHelper){
        String time = sdf.format(new Date());
        int begintime_hour;
        int begintime_minute;
        int endtime_hour;
        int endtime_minute;
        int now_hour;
        int now_minute;
        String littercar_money1;
        String littercar_money2;
        String littercar_money = "不存在";
        String s[] = time.split(":");
        now_hour = Integer.parseInt(s[0]);
        now_minute = Integer.parseInt(s[1]);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select * from time1",null);
        Cursor cursor2 = db.rawQuery("select * from time2",null);
        if(cursor1.moveToFirst()){
            
            return cursor1.getString(1);
        }else {
            if(cursor2.moveToFirst()){
                String bt[] = cursor2.getString(1).split("-");
                String et[] = cursor2.getString(2).split("-");
                begintime_hour=Integer.parseInt(bt[0]);
                begintime_minute=Integer.parseInt( bt[1]);
                endtime_hour = Integer.parseInt(et[0]);
                endtime_minute = Integer.parseInt(et[1]);
                littercar_money1 = cursor2.getString(3);
                littercar_money2 = cursor2.getString(5);
                if(now_hour>begintime_hour&&now_hour<endtime_hour){
                    littercar_money= littercar_money1;
                }
                else if(now_hour==begintime_hour&&(now_minute>=begintime_minute)){
                 
                        littercar_money= littercar_money1;
                }
               else if(now_hour==endtime_hour&&now_minute<=endtime_minute){
                    littercar_money = littercar_money1;
                }
                else {
                    littercar_money=littercar_money2;
                }
            }
        }
       return littercar_money;
    }
}
