package com.aiseminar.platerecognizer.action;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aiseminar.db.MyOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/17.
 */

public class Computemoney {
    public int getmoney(MyOpenHelper myOpenHelper, String type, int minute) throws ParseException {
        int begintime_hour;
        int endtime_hour;
        int hour=minute/60;
        int money = 0;
        int littercar_money1;
        int littercar_money2;
        int bigcar_money1;
        int bigcar_money2;
        SimpleDateFormat sdf3;
        Date d1=null;
        Date d2 = null;
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select * from time1", null);
        Cursor cursor2 = db.rawQuery("select * from time2", null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf3 =new SimpleDateFormat("HH-mm");
        if (cursor1.moveToFirst()) {
            if (type.equals("小型车")) {
                if(minute>15&&minute<60){
                    money=Integer.parseInt(cursor1.getString(1));
                }else {
                money = Integer.parseInt(cursor1.getString(1)) * hour;
                }
                if(minute%60>15){
                    money = money+Integer.parseInt(cursor1.getString(1));
                }

            } else {
                if(minute>15&&minute<60){
                    money = Integer.parseInt(cursor1.getString(2));
                }else {
                money = Integer.parseInt(cursor1.getString(2)) * hour;

                }
                if(minute%60>15){
                    money = money+Integer.parseInt(cursor1.getString(2));
                }

            }
        }
        else {
            if (cursor2.moveToFirst()) {
                d1 = sdf3.parse(cursor2.getString(1));
                d2 = sdf3.parse(cursor2.getString(2));
                String bt[] = cursor2.getString(1).split("-");
                String et[] = cursor2.getString(2).split("-");
                begintime_hour = Integer.parseInt(bt[0]);
                endtime_hour = Integer.parseInt(et[0]);
                littercar_money1 = Integer.parseInt(cursor2.getString(3));
                bigcar_money1 = Integer.parseInt(cursor2.getString(4));
                bigcar_money2 = Integer.parseInt(cursor2.getString(6));
                littercar_money2 = Integer.parseInt(cursor2.getString(5));
                long diff = d2.getTime() - d1.getTime();
                long days = diff / (1000 * 60 * 60 );
                if(type.equals("小型车")){
                    if(hour>24){
                        if((hour%24)<=begintime_hour){
                        money= (int) ((days*littercar_money1+(24-days)*littercar_money2)*(hour/24)+littercar_money2*(hour%24));
                            if(minute%60>15){
                                money = money+littercar_money2;
                            }
                        }
                        else if ((hour%24)>begintime_hour&&(hour%24)<endtime_hour){
                            money= (int) ((days*littercar_money1+(24-days)*littercar_money2)*(hour/24)+littercar_money2*begintime_hour+littercar_money1*((hour%24)-begintime_hour));

                            if(minute%60>15){
                                money = money+littercar_money1;
                            }
                        }
                        else {
                            money= (int) ((days*littercar_money1+(24-days)*littercar_money2)*(hour/24)+littercar_money2*begintime_hour+littercar_money1*days+((hour%24)-endtime_hour)*littercar_money2);
                            if(minute%60>15){
                                money = money+littercar_money2;
                            }
                        }
                    }else {
                        if(hour<=begintime_hour){
                            if(minute>15&&minute<60){
                                money=littercar_money2;
                            }
                           else{ money= (int) (littercar_money2*hour);
                                if(minute%60>15){
                                    money = money+littercar_money2;
                                }
                           }
                        }
                        else if (

                                hour>begintime_hour&&hour<endtime_hour

                                ){

                            money= (int) (littercar_money2*begintime_hour+littercar_money1*(hour-begintime_hour));
                            if(minute%60>15){
                                money = money+littercar_money1;
                            }
                        }
                        else {
                            money= (int) (littercar_money2*begintime_hour+littercar_money1*days+(hour-endtime_hour)*littercar_money2);
                            if(minute%60>15){
                                money = money+littercar_money2;
                            }
                        }
                    }
                }
                else {
                    if(hour>24){
                        if((hour%24)<=begintime_hour){
                            money= (int) ((days*bigcar_money1+(24-days)*bigcar_money2)*(hour/24)+bigcar_money2*(hour%24));
                            if(minute%60>15){
                                money = money+bigcar_money2;
                            }
                        }
                        else if ((hour%24)>begintime_hour&&(hour%24)<endtime_hour){
                            money= (int) ((days*bigcar_money1+(24-days)*bigcar_money2)*(hour/24)+bigcar_money2*begintime_hour+bigcar_money1*((hour%24)-begintime_hour));
                            if(minute%60>15){
                                money = money+bigcar_money1;
                            }
                        }
                        else {
                            money= (int) ((days*bigcar_money1+(24-days)*bigcar_money2)*(hour/24)+bigcar_money2*begintime_hour+bigcar_money1*days+((hour%24)-endtime_hour)*bigcar_money2);
                            if(minute%60>15){
                                money = money+bigcar_money2;
                            }
                        }
                    }else {
                        if(hour<=begintime_hour){
                            money= (int) (bigcar_money2*hour);
                            if(minute%60>15){
                                money = money+bigcar_money2;
                            }
                        }
                        else if (hour>begintime_hour&&hour<endtime_hour){
                            money= (int) (bigcar_money2*begintime_hour+bigcar_money1*(hour-begintime_hour));
                            if(minute%60>15){
                                money = money+bigcar_money1;
                            }
                        }
                        else {
                            money= (int) (bigcar_money2*begintime_hour+bigcar_money1*days+(hour-endtime_hour)*bigcar_money2);
                            if(minute%60>15){
                                money = money+bigcar_money2;
                            }
                        }
                    }

                }
            }

        }

        return money;
    }
}