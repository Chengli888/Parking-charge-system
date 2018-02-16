package com.aiseminar.platerecognizer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import com.aiseminar.platerecognizer.R;
import com.aiseminar.platerecognizer.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/12.
 */

public class TimeSetting extends BaseActivity implements View.OnClickListener{

    private Button set_tab_01;
    private Button set_tab_02;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeinterval);
        initView();
        initEvent();
    }

    private void initEvent() {
        set_tab_01.setOnClickListener(this);
        set_tab_02.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        //tabs
        set_tab_01 = (Button) findViewById(R.id.bt_singletime);
        set_tab_02 = (Button) findViewById(R.id.bt_twotime);
        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new tab01Fragment();
        Fragment mTab02 = new tab02Fragment();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
      mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
          @Override
          public android.support.v4.app.Fragment getItem(int position) {
              return mFragments.get(position);
          }

          @Override
          public int getCount() {
              return mFragments.size();
          }
      };
        mViewPager.setAdapter(mAdapter);

      }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.bt_singletime:
                 mViewPager.setCurrentItem(0);
                 break;
             case R.id.bt_twotime:
                 mViewPager.setCurrentItem(1);
                 break;
             default:
                 break;
         }
    }
}