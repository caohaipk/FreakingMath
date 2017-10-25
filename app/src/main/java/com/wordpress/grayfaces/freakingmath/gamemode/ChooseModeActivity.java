package com.wordpress.grayfaces.freakingmath.gamemode;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.wordpress.grayfaces.freakingmath.R;

public class ChooseModeActivity extends FragmentActivity {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());

        Tab = (ViewPager)findViewById(R.id.pager);
        final Button btnNext = (Button) findViewById(R.id.btn_next);
        final Button btnFinish = (Button) findViewById(R.id.btn_finish);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {

                        //actionBar = getActionBar();
                        //actionBar.setSelectedNavigationItem(position);
                        if (position+1<TabAdapter.getCount()){
                            btnNext.setVisibility(View.VISIBLE);
                            btnFinish.setVisibility(View.GONE);
                        } else {
                            btnNext.setVisibility(View.GONE);
                            btnFinish.setVisibility(View.VISIBLE);
                        }
                    }
                });
        Tab.setAdapter(TabAdapter);

        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

                Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub

            }};
        //Add New Tab
        //actionBar.addTab(actionBar.newTab().setText("StartMultipleChoice").setTabListener(tabListener));
        //actionBar.addTab(actionBar.newTab().setText("StartTrueFalse").setTabListener(tabListener));
        //actionBar.addTab(actionBar.newTab().setText("Windows").setTabListener(tabListener));

    }

}
