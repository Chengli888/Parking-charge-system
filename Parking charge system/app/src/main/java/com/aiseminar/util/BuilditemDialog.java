package com.aiseminar.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.aiseminar.platerecognizer.R;
import com.aiseminar.platerecognizer.action.Update;
import com.cynovo.kivvidevicessdk.KvException;

import java.io.UnsupportedEncodingException;

/**
 * Created by 18852 on 2017/3/21.
 */

public class BuilditemDialog  {
    public  Dialog BuilditemDialog(Context context, final String plate, final String username, final String stream, final String begintime, final String endTime,
                                   final String charge , final String location, final String chargeway , final String color ){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final Update up = new Update();
        builder.setIcon(R.drawable.park);
        builder.setTitle("请您选择付款方式");
        final  String[] items = new String[]{
                "微信",
                "支付宝",
                "现金"
        };
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String bt =begintime;
                String et =endTime;
                switch (which){
                    case 0:
                        try {

                            Print print = new Print(stream,plate,"微信",Integer.parseInt(charge),"cl1210276648",bt.replaceAll("%20"," "),et.replaceAll("%20"," "),color);
                            up.update(plate,username,stream,begintime,endTime,charge,location,"微信",color);
                            break;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (KvException e) {
                            e.printStackTrace();
                        }
                    case 1:
                        try {
                            Print print = new Print(stream,plate,"支付宝",Integer.parseInt(charge),"18852951220",bt.replaceAll("%20"," "),et.replaceAll("%20"," "),color);
                            up.update(plate,username,stream,begintime,endTime,charge,location,"支付宝",color);
                            break;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (KvException e) {
                            e.printStackTrace();
                        }
                    case 2:
                        try {
                            Print print = new Print(stream,plate,"现金",Integer.parseInt(charge),"欢迎使用现金来付款",bt.replaceAll("%20"," "),et.replaceAll("%20"," "),color);
                            up.update(plate,username,stream,begintime,endTime,charge,location,"现金",color);
                            break;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (KvException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
