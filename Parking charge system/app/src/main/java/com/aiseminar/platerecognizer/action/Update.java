package com.aiseminar.platerecognizer.action;

import com.aiseminar.util.ConstantValue;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;;
import java.net.URLEncoder;

/**
 * Created by 18852 on 2017/3/23.
 */

public class Update {
    public void update(final String plate, final String username, final String stream, final String begintime, final String endTime,
                       final String money , final String location, final String chargeway , final String color ){

                 final String URL =  ConstantValue.URL2+"?plate="+plate+"&username="+username+"&color="+color+
                    "&location="+location+"&chargeway="+chargeway+"&begintime="+begintime+"&endTime="+endTime+"&money="+money+"&stream="+stream;
            //final String s = URLEncoder.encode(URL);
             System.out.println(URL);
            new Thread(new Runnable() {
                @Override
                public void run() {


                    HttpUtils httpUtils = new HttpUtils();

                    httpUtils.send(HttpRequest.HttpMethod.POST, URL, new RequestCallBack<String>() {

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if(responseInfo.statusCode == 200){
                                System.out.println(URL);
                                System.out.println("--------------------------------success");

                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            System.out.println("--------------------------------failure-------");
                        }
                    });
                }
            }).start();

        }


    }

