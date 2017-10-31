package com.wordpress.grayfaces.freakingmath.gamemode;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.wordpress.grayfaces.freakingmath.R;

public class ChooseModeActivity extends FragmentActivity {
    private TabPagerAdapter TabAdapter;
    private ViewPager tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);
         TabAdapter = new TabPagerAdapter(getSupportFragmentManager());

        tab = (ViewPager) findViewById(R.id.pager);
        final Button btnNext = (Button) findViewById(R.id.btn_next);
        final Button btnFinish = (Button) findViewById(R.id.btn_finish);
        tab.setOnPageChangeListener(
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
        tab.setAdapter(TabAdapter);

        //Enable Tabs on Action Bar
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }
    @Override
    public void onStop(){
        super.onStop();
        System.out.println("des choos");
        tab.setOnPageChangeListener(null);
        tab=null;
        TabAdapter=null;
    }

}
