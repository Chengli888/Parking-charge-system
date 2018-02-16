package com.aiseminar.platerecognizer.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiseminar.db.MyOpenHelper;
import com.aiseminar.platerecognizer.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class tab02Fragment extends Fragment {
    private EditText bigcar1;
    private EditText littercar1;
    private EditText bigcar2;
    private EditText littercar2;
    private Button bt_twotime;
    private EditText begin_time;
    private EditText end_time;
    private MyOpenHelper myOpenHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_02,container,false);
        bigcar1 = (EditText) v.findViewById(R.id.bigcar1);
        bigcar2 = (EditText) v.findViewById(R.id.bigcar2);
        littercar1 = (EditText) v.findViewById(R.id.littercar1);
        littercar2 = (EditText) v.findViewById(R.id.littercar2);
        begin_time = (EditText) v.findViewById(R.id.begin_time);
        end_time = (EditText) v.findViewById(R.id.end_time);
        bt_twotime = (Button) v.findViewById(R.id.bt_twotime);
        myOpenHelper = new MyOpenHelper(getContext());
        bt_twotime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twotime();

            }
        });

        return v;
    }

    private void twotime() {
        String bt[]=begin_time.getText().toString().split("-");
        String et[]=end_time.getText().toString().split("-");
        if(TextUtils.isEmpty(bigcar1.getText().toString())||TextUtils.isEmpty(bigcar2.getText().toString())||TextUtils.isEmpty(littercar2.getText().toString())||TextUtils.isEmpty(littercar1.getText().toString())||TextUtils.isEmpty(begin_time.getText().toString())
                ||TextUtils.isEmpty(end_time.getText().toString())){
            Toast.makeText(getContext(),"请将空格填写完整",Toast.LENGTH_SHORT).show();
        }
        else if(!(begin_time.getText().toString().matches("^(([0-1][0-9])|([1-2][0-3]))-([0-5][0-9])$")&&end_time.getText().toString().matches("^(([0-1][0-9])|([1-2][0-3]))-([0-5][0-9])$"))){
            Toast.makeText(getContext(),"请输入正确的时间格式",Toast.LENGTH_SHORT).show();
        }
        else if (!(bt[1].equals("00")&&et[1].equals("00"))){
            buildAlertDialog().show();
        }
        else {
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            db.execSQL("delete from time1 where 1=1");
            db.execSQL("delete from time2 where 1=1");

            int begin_hour=Integer.parseInt(bt[0]);
            int end_hour = Integer.parseInt(et[0]);
            if(begin_hour<end_hour){
                db.execSQL("delete from time1 where 1=1");
                db.execSQL("delete from time2 where 1=1");
            db.execSQL("insert into time2(begin_time,end_time,littercar1,bigcar1,littercar2,bigcar2) values(?,?,?,?,?,?)",new Object[]{
                    begin_time.getText().toString(),end_time.getText().toString() , littercar1.getText().toString(),bigcar1.getText().toString(),littercar2.getText().toString(),bigcar2.getText().toString()
            });
            Toast.makeText(getContext(),"时间段收费设置成功",Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setClass(getContext(),ParkActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
            else {
                Toast.makeText(getContext(),"起始时间必须小于于终止时间，且相差不能低于一个小时",Toast.LENGTH_LONG).show();}
        }
        }
    private Dialog buildAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.park);
        builder.setTitle("对话框");
        builder.setMessage("输入的格式必须为整点格式，如10-00");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
    }

