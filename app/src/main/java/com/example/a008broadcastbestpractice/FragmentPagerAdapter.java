package com.example.a008broadcastbestpractice;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
    List<Fragment> fragments;
    private final int PAGER_COUNT = 3;

    public FragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    //构造，引入list
    //判定F在屏幕内外pause和resume
    //加载大图
    //relative
    //后台服务受限，
    //main设置pro优先级（有序）
    //本地广播
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
