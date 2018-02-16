package com.aiseminar.platerecognizer.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.aiseminar.platerecognizer.R;
import com.aiseminar.util.ConstantValue;
import com.aiseminar.util.KV;
import com.aiseminar.util.SpUtils;
import com.cynovo.kivvidevicessdk.KivviDevice;
import com.cynovo.kivvidevicessdk.KvException;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/11/8.
 */

public class ParkActivity extends Activity {
    private ListView parkui_list;
    private List<String> parkuiList;
    private TextView parkui_item_list;
    @ViewInject(R.id.tv_username)
    private TextView tv_username;
    @ViewInject(R.id.tv_location)
    private TextView tv_location;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkui);
        ViewUtils.inject(this);
        KivviDevice KD = new KivviDevice();
        try {
            KD.Action(KV.CMD.DISPLAY_IDLE);
        } catch (KvException e) {
            e.printStackTrace();
        }
        tv_username.setText(SpUtils.getString(getApplicationContext(),ConstantValue.USERNAME,""));
        tv_location.setText(SpUtils.getString(getApplicationContext(),ConstantValue.LOCATION,""));
       parkui_list = (ListView) findViewById(R.id.parkui_list);
        parkuiList = new ArrayList<String>();
       parkuiList.add("拍照识别");
        parkuiList.add("手动录入");
        parkuiList.add("收费设置");
        parkuiList.add("停车浏览");
        parkuiList.add("退出登录");
        parkui_list.setAdapter(new MyAdapter());
        parkui_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parkuiList.get(position).equals("拍照识别")){
                    Intent intent=new Intent();
                  intent.setClass(ParkActivity.this,CameraActivity.class);
                    startActivity(intent);

                }
                else  if (parkuiList.get(position).equals("手动录入")){
                    Intent intent=new Intent();
                    intent.setClass(ParkActivity.this,HandIputActivity.class);
                     startActivity(intent);

                }else if(parkuiList.get(position).equals("收费设置")){
                    Intent intent=new Intent();
                    intent.setClass(getApplicationContext(),TimeSetting.class);
                    startActivity(intent);
                }else if(parkuiList.get(position).equals("退出登录")){
                    SpUtils.putBoolean(getApplicationContext(),ConstantValue.ISONLINE,false);
                    Intent intent=new Intent();
                    intent.setClass(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent=new Intent();
                    intent.setClass(getApplicationContext(),InformationActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    //对话框的使用
//    private Dialog buildAlertDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.ic_launcher);
//        builder.setTitle("对话框");
//        builder.setMessage("您的密码不对!!");
//
///**左边按钮*/
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                setTitle("您点击的是左边确定按钮!");
//            }
//        });
///**右边按钮*/
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                setTitle("您点击的是右边取消按钮!");
//            }
//        });
//        return builder.create();
//    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return parkuiList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView==null){
                view = View.inflate(ParkActivity.this,R.layout.parkui_item,null);
            }else {
                view =convertView;
            }
            parkui_item_list = (TextView) view.findViewById(R.id.parkui_item_list);
            parkui_item_list.setText(parkuiList.get(position));
            return view;
        }
    }
}
