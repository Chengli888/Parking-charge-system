package com.aiseminar.platerecognizer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aiseminar.Bean.User;
import com.aiseminar.platerecognizer.R;
import com.aiseminar.util.ConstantValue;
import com.aiseminar.util.SpUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by 18852 on 2017/3/8.
 */

public class LoginActivity extends Activity {
    @ViewInject(R.id.et_name)
    private EditText et_name ;
    @ViewInject(R.id.button)
    private Button button;
    @ViewInject(R.id.et_password)
    private EditText et_password;
    @ViewInject(R.id.progressBar)
    private ProgressBar progressBar;
    private Boolean flag =false;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        //SpUtils.putBoolean(getApplicationContext(),ConstantValue.ISONLINE,true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Message msg = new Message();
                            final Gson gson = new Gson();
                            HttpUtils http = new HttpUtils();
                            http.send(HttpRequest.HttpMethod.GET, ConstantValue.URL + "?username="+et_name.getText()+"&password="+et_password.getText(), new RequestCallBack<String>() {
                                @Override
                                public void onSuccess(ResponseInfo<String> responseInfo) {
                                    User user = gson.fromJson(responseInfo.result, User.class);
                                    String username = user.getUsername();
                                    String password = user.getPassword();
                                    String location = user.getLocation();
                                    SpUtils.putString(getApplicationContext(), ConstantValue.PASSWORD, password);
                                    SpUtils.putString(getApplicationContext(), ConstantValue.USERNAME, username);
                                    SpUtils.putString(getApplicationContext(), ConstantValue.LOCATION, location);
                                    //SpUtils.putBoolean(getApplicationContext(),ConstantValue.ISONLINE,true);
                                    flag =true;
                                    System.out.println(flag);
                                    System.out.println(user);
                                    msg.obj =flag;
                                    mHandler.sendMessage(msg);
                                }

                                @Override
                                public void onFailure(HttpException e, String s) {
                                    SpUtils.putBoolean(getApplicationContext(),ConstantValue.ISONLINE,false);
                                    msg.obj =flag;
                                    System.out.println("失败了————————————————————————————————————");
                                    mHandler.sendMessage(msg);
                                }


                            }
                            );
                    }
                }).start();}
        });
        mHandler =  new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                flag = (Boolean) msg.obj;
                progressBar.setVisibility(View.VISIBLE);
                if( flag){
                    SpUtils.putBoolean(getApplicationContext(),ConstantValue.ISONLINE,true);
                    Intent intent = new Intent(getApplicationContext(),ParkActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    buildAlertDialog().show();
                    //Toast.makeText(getApplicationContext(),"密码或者登录名不正确请您确认",Toast.LENGTH_LONG).show();
                }
            }
        };


    }
    private Dialog buildAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.park);
        builder.setTitle("对话框");
        builder.setMessage("密码或者登录名不正确请您确认");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                et_name.setText("");
                et_password.setText("");
                et_password.setText("");
            }
        });
        return builder.create();
    }
}
