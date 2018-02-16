package com.aiseminar.platerecognizer.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.aiseminar.platerecognizer.R;
import com.aiseminar.util.ConstantValue;
import com.aiseminar.util.KV;
import com.aiseminar.util.SpUtils;
import com.cynovo.kivvidevicessdk.KivviDevice;
import com.cynovo.kivvidevicessdk.KvException;

/**
 * Created by 18852 on 2017/2/28.
 */

public class SplashActivity extends Activity {
    private RelativeLayout rlRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.aiseminar.platerecognizer.R.layout.activity_splash);
        final KivviDevice kvDev = new KivviDevice();
        try {
            kvDev.Action(KV.CMD.DISPLAY_IDLE);
        } catch (KvException e) {
            e.printStackTrace();
        }
        rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        //旋转动画
        RotateAnimation animRotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF
        ,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animRotate.setDuration(2000);
        animRotate.setFillAfter(true);

        //缩放动画
        ScaleAnimation animScale = new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF,0.5f
                ,Animation.RELATIVE_TO_SELF,0.5f
                );
        animScale.setDuration(2000);
        animScale.setFillAfter(true);

        //渐变动画
        AlphaAnimation animAlpha = new AlphaAnimation(0,1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        AnimationSet set =new AnimationSet(true);
        set.addAnimation(animAlpha);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        rlRoot.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //SpUtils.putBoolean(getApplicationContext(),ConstantValue.ISONLINE,false);
                if(!SpUtils.getBoolean(getApplicationContext(),ConstantValue.ISONLINE,false)){

                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    System.out.println("---------mmhjbhybyyy"+SpUtils.getString(getApplicationContext(),ConstantValue.LOCATION,"")+"--------------"+SpUtils.getString(getApplicationContext(),ConstantValue.USERNAME,""));
                    Intent intent = new Intent(getApplicationContext(),ParkActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
