package com.aiseminar.platerecognizer.ui;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.aiseminar.db.MyOpenHelper;
import com.aiseminar.platerecognizer.R;
import com.aiseminar.platerecognizer.action.Bigcar_money;
import com.aiseminar.platerecognizer.action.Computemoney;
import com.aiseminar.platerecognizer.action.Littercar_money;
import com.aiseminar.platerecognizer.action.Update;
import com.aiseminar.util.BuilditemDialog;
import com.aiseminar.util.ConstantValue;
import com.aiseminar.util.KV;
import com.aiseminar.util.Print;
import com.aiseminar.util.SpUtils;
import com.cynovo.kivvidevicessdk.KivviDevice;
import com.cynovo.kivvidevicessdk.KvException;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
//车的信息的预设
public class CheckinActivity extends Activity {
    private MyOpenHelper myOpenHelper;
    private String plate;
    private String[] s;
    private String type;
    private TextView ed_money;
    private EditText ed_begintime;
    private EditText ed_carnumber;
    private List<Information> infoList;
    private Button bt_checkout;
    private RadioButton bigcar;
    private  RadioButton littercar;
    private  Littercar_money lm;
    private Bigcar_money bm;
    SimpleDateFormat sdf2;
    final KivviDevice kvDev = new KivviDevice();
    private Boolean flag =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        KivviDevice KD = new KivviDevice();
        try {
            KD.Action(KV.CMD.DISPLAY_IDLE);
        } catch (KvException e) {
            e.printStackTrace();}
        myOpenHelper = new MyOpenHelper(CheckinActivity.this);
        ed_carnumber = (EditText) findViewById(R.id.ed_carnumber);
        ed_begintime = (EditText) findViewById(R.id.ed_begintime);
        ed_money = (TextView) findViewById(R.id.ed_money);
        Button bt_checkin = (Button) findViewById(R.id.bt_checkin);
        TextView tv_type = (TextView) findViewById(R.id.tv_type);
         littercar = (RadioButton) findViewById(R.id.littercar);
         bigcar = (RadioButton) findViewById(R.id.bigcar);
         bt_checkout = (Button) findViewById(R.id.bt_checkout);
        //对information封装
          infoList = new ArrayList<Information>();
        //获取传来的车牌信息
        Intent intent = getIntent();
        plate = intent.getStringExtra("plate");
        //对字符串进行分割处理
        s = plate.split(":");
        tv_type.setText(s[0].trim());
        ed_carnumber.setText(s[1].trim());
        //时间设置
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf2=new SimpleDateFormat("HH:mm");
        lm = new Littercar_money();
        bm = new Bigcar_money();
        //System.out.println("-----------"+lm.getmoney(sdf2,myOpenHelper));
        ed_begintime.setText(sdf.format(new Date()));

        //radiobutton的设置
        if (littercar.isChecked()) {
            if(!(lm.getmoney(sdf2,myOpenHelper) .equals("不存在"))){
                ed_money.setText(lm.getmoney(sdf2,myOpenHelper)+"元/小时");
                type = "小型车";}else {
                flag = false;
            }
        }
        littercar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_money.setText(lm.getmoney(sdf2,myOpenHelper)+"元/小时");

            }
        });
        bigcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_money.setText(bm.getmoney(sdf2,myOpenHelper)+"元/小时");
                type = "大型车";

            }
        });
        bt_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("-----------"+lm.getmoney(sdf2,myOpenHelper)+sdf2.format(new Date()));
                //System.out.println("-----------"+bm.getmoney(sdf2,myOpenHelper)+sdf2.format(new Date()));
                if(!TextUtils.isEmpty(ed_carnumber.getText())){
                    insert();
                    Intent intent  = new Intent();
                    intent.setClass(getApplicationContext(),InformationActivity.class);
                    startActivity(intent);
                }  else {
                    Toast.makeText(getApplicationContext(),"输入的值不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_checkout.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               try {
                   if(!TextUtils.isEmpty(ed_carnumber.getText())){
                       charge();
                   }
                   else {
                       Toast.makeText(getApplicationContext(),"输入的值不能为空",Toast.LENGTH_SHORT).show();
                   }
               } catch (ParseException e) {
                   e.printStackTrace();
               } catch (KvException e) {
                   e.printStackTrace();
                   System.out.println("-----------------------------出问题啦");
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }
           }
        });
        if(!flag){
            buildAlertDialog().show();
        }

    }
    private Dialog buildAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.park);
        builder.setTitle("对话框");
        builder.setMessage("请您输入各个时间段的收费标准");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(),TimeSetting.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    private void charge() throws ParseException, KvException, UnsupportedEncodingException {
        Date beginDate = null;
        Date endDate = null;
        String color="";
        int charging = 0;
        String[] c;
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
        String dbgData = "18852951220";
        String chargeway = "支付宝";
        String begintime = null;
        String types = null;
        int charge = 0;
        String stream = "666";
        String  plate = ed_carnumber.getText().toString();
        String username =SpUtils.getString(getApplicationContext(), ConstantValue.USERNAME,"");
        String location =SpUtils.getString(getApplicationContext(), ConstantValue.LOCATION,"");
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select time,type,color from info2 where plate=?", new String[]{ed_carnumber.getText().toString()});
        Cursor cursor2 = db.rawQuery("select * from time2",null);
        if (cursor2.moveToFirst()){
            System.out.println("-------------"+cursor2.getString(1)+cursor2.getString(2)+cursor2.getString(3)+cursor2.getString(4)+cursor2.getString(5)+cursor2.getString(6));
        }
        if (cursor.moveToFirst()) {
            begintime = cursor.getString(0);
            types = cursor.getString(1);
            color = cursor.getString(2);

            if (types.equals("小型车")) {
                littercar.setChecked(true);
            } else {
                bigcar.setChecked(true);
            }
            String endTime = ed_begintime.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stream =  SpUtils.getString(getApplicationContext(), ConstantValue.USERNAME,"")+"_"+sdf3.format(new Date());
            beginDate = sdf.parse(begintime);
            endDate = sdf.parse(endTime);
            long diff = endDate.getTime() - beginDate.getTime();
            //总共停留的时间
            int minute =Math.abs( Integer.parseInt(String.valueOf(diff / (1000 * 60  ))));
            //收费金额
            Computemoney cp = new Computemoney();
            charge = cp.getmoney(myOpenHelper,types,minute);

            // System.out.println("收费为————————————————————————————————————————"+begintime+endTime+charge+types);
            System.out.println("开始打印了");
            BuilditemDialog BID = new BuilditemDialog();
            begintime = begintime.replaceAll(" ","%20");
            endTime = endTime.replaceAll(" ","%20");
            BID.BuilditemDialog(this,plate,username,stream,begintime,endTime,String.valueOf(charge),location,chargeway,color).show();
            Toast.makeText(getApplicationContext(), "此车为" + types + "停车" + minute/60 + "小时" + "收费" + charge + "元", Toast.LENGTH_LONG).show();
            db.execSQL("delete from info2 where plate=?",new Object[]{ed_carnumber.getText()});
        } else {
            Toast.makeText(getApplicationContext(), "这两车不存在，请确认车牌号在输入", Toast.LENGTH_LONG).show();
        }

    }

    private void insert() {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //将数据插入数据库
        db.execSQL("insert into info2(time,plate,color,type,money) values(?,?,?,?,?)", new Object[]{
                ed_begintime.getText(), s[1], s[0], type, ed_money.getText()
        });

}
//    private  void update(final String plate, final String username, final String stream, final String begintime, final String endTime, final int money , final String location, final String chargeway , final String color ){
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//
////                            Class.forName("com.mysql.jdbc.Driver");
////                            String url="jdbc:mysql://10.16.23.182:3306/parking_charge?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8";//链接数据库语句
////                            Connection conn=  (Connection) DriverManager.getConnection(url);
////                            conn = (Connection) DriverManager.getConnection(url);
////                            String sql = "insert into car_information(plate,username,stream,pin_time,pout_time,money,location,chargeway,color)values(?,?,?,?,?,?,?,?,?)";
////                            PreparedStatement PS = (PreparedStatement) conn.prepareStatement(sql);
////                            PS.setString(1,ed_carnumber.getText().toString());
////                            PS.setString(2, SpUtils.getString(getApplicationContext(), ConstantValue.USERNAME,""));
////                            PS.setString(3,stream);
////                            //System.out.println("----------------------------------------"+stream);
////                            PS.setString(4,begintime);
////                            PS.setString(5,endTime);
////                            PS.setString(6, String.valueOf(money));
////                            PS.setString(7,SpUtils.getString(getApplicationContext(), ConstantValue.LOCATION,""));
////                            PS.setString(8,chargeway);
////                            PS.setString(9,color);
////                            PS.execute();
////                            PS.close();
////                            conn.close();
//
//                    }
//                }
//        ).start();
//    }
}
