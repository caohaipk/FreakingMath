package com.wordpress.grayfaces.freakingmath.app;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.view.View;

/**
 * Project FreakingMath
 * Created by Gray on 10/19/2017.
 */

public class Utility {
    public static void hideNavigationBar(Activity activity, ActionBar actionBar){
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    public static void setFullScreen(Activity activity, ActionBar actionBar){
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
