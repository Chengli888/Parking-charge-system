package com.aiseminar.platerecognizer.ui;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class tab01Fragment extends Fragment {
    private Button bt_single;
    private EditText pm_littercar;
    private EditText pm_bigcar;
    private MyOpenHelper myOpenHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.tab_01,container,false);
          bt_single = (Button) v.findViewById(R.id.bt_single);
          pm_bigcar = (EditText) v.findViewById(R.id.pm_bigcar);
          pm_littercar = (EditText) v.findViewById(R.id.pm_littercar);
        myOpenHelper = new MyOpenHelper(getContext());
        bt_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  st_insert();


            }
        });
        return v;
    }

    private void st_insert() {
        if(pm_bigcar.getText().toString().equals("")||pm_littercar.getText().toString().trim().equals("")){
        Toast.makeText(getContext(),"请输入有效值",Toast.LENGTH_LONG).show();
        }else {
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            db.execSQL("delete from time1 where 1=1");
            db.execSQL("delete from time2 where 1=1");
            db.execSQL("insert into time1(pm_littercar,pm_bigcar) values(?,?)",new Object[]{
                    pm_littercar.getText() ,pm_bigcar.getText()
            });
            Toast.makeText(getContext(),"时间段收费设置成功",Toast.LENGTH_LONG).show();
            Cursor cursor = db.rawQuery("select * from time1",null);
            if(cursor.moveToFirst()){
                System.out.println(cursor.getString(1)+"---------------"+cursor.getString(2));
            }
            Intent intent=new Intent();
            intent.setClass(getContext(),ParkActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
