package com.aiseminar.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 18852 on 2017/3/10.
 */

public class SpUtils {
    private static SharedPreferences sp;
    public static void putBoolean(Context ctx, String key, boolean value){
        if(sp==null){
            sp = ctx.getSharedPreferences("con",Context.MODE_PRIVATE);}
        sp.edit().putBoolean(key,value).commit();
    }
    public static Boolean getBoolean(Context ctx,String key,boolean defvalue){
        if(sp==null){
            sp = ctx.getSharedPreferences("con",Context.MODE_PRIVATE);}
        return sp.getBoolean(key,defvalue);
    }
    public static void putString(Context ctx,String key,String value){
        if(sp==null){
            sp = ctx.getSharedPreferences("con",Context.MODE_PRIVATE);}
        sp.edit().putString(key,value).commit();
    }
    public static String getString(Context ctx,String key,String defvalue){
        if(sp==null){
            sp = ctx.getSharedPreferences("con",Context.MODE_PRIVATE);}
        return sp.getString(key,defvalue);
    }

}
