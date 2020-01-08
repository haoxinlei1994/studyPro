package com.mrh.chapter4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by haoxinlei on 2020-01-07.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return StudyFragment.newInstance("学习");
        } else {
            return StudyFragment.newInstance("看书");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
