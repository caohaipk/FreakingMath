package com.wordpress.grayfaces.freakingmath.gamemode;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Project FreakingMath
 * Created by Gray on 10/24/2017.
 */

class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for StartMultipleChoice Tab
                return new StartMultipleChoice();
            case 1:
                //Fragment for StartTrueFalse Tab
                return new StartTrueFalse();
//            case 2:
//                //Fragment for Windows Tab
//                return new Windows();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2; //No of Tabs
    }

}
