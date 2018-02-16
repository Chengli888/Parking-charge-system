package com.aiseminar.platerecognizer.ui;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aiseminar.db.MyOpenHelper;
import com.aiseminar.platerecognizer.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/9.
 */

public class InformationActivity extends Activity {
    private MyOpenHelper myOpenHelper;
    private TextView info_time;
    private TextView info_plate;
    private TextView info_color;
    private TextView info_type;
    private TextView info_money;
    private List<Information> infoList;
    private ListView carpark_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        myOpenHelper = new MyOpenHelper(getApplicationContext());
        carpark_info = (ListView) findViewById(R.id.carpark_info);
        //对information封装
        infoList = new ArrayList<Information>();
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //从数据查找数据
        Cursor cursor = db.rawQuery("select * from info2", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Information info =new Information();
                info.setTime(cursor.getString(1));
                info.setPlate(cursor.getString(2));
                info.setColor(cursor.getString(3));
                info.setType(cursor.getString(4));
                //info.setMoney(cursor.getString(5));
                infoList.add(info);

            }
        }
        carpark_info.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return infoList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null){
                view = View.inflate(InformationActivity.this,R.layout.item,null);
            }else {
                view = convertView;
            }
            info_time = (TextView) view.findViewById(R.id.info_time);
            info_type = (TextView) view.findViewById(R.id.info_type);
            info_color = (TextView)view.findViewById(R.id.info_color);
            //info_money = (TextView)view.findViewById(R.id.info_money);
            info_plate = (TextView)view.findViewById(R.id.info_plate);
            //用控件来显示数据
           Information info = infoList.get(position);
            info_time.setText(info.getTime());
            info_color.setText(info.getColor());
            //info_money.setText(info.getMoney());
            info_plate.setText(info.getPlate());
            info_type.setText(info.getType());
            return view;
        }
    }


}
