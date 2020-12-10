package com.example.a008broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    ViewPager viewPager;
    Button button1, button2, button3;
    List fragments = new ArrayList<>();
    RadioGroup radioGroup;

    private List<View> viewList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager  = findViewById(R.id.vp);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        radioGroup = findViewById(R.id.home_rg);

        Fragment1 fragment1 = new Fragment1();
        fragments.add(fragment1);
        Fragment2 fragment2 = new Fragment2();
        fragments.add(fragment2);
        Fragment3 fragment3 = new Fragment3();
        fragments.add(fragment3);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()));

        viewPager.setCurrentItem(0);
        //设置滑动事件,使切换的页面跟底部对应
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            //这个方法就是页面滑动之后底部导航栏的一个状态切换
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        //如果在第一个页面,即底部第一个控件被选择设置他的图案为home_selecter
                        //反之,设置为unselect,背景颜色也与其他控件区分,以便于区分选择的区域
                        break;
                    case 1:
                        //意义同case 0
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置点击事件,切换viewPager
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });


        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.a008broadcastbestpractice.FORCE_OFFLINE");sendBroadcast(intent);
            }
        });
    }
}