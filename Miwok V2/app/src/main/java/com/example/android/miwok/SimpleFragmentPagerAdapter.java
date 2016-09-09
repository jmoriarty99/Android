package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by johnmoriarty on 9/8/16.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount(){
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new NumbersFragment();
        } else {
            return new NumbersFragment();
        }
    }

}
