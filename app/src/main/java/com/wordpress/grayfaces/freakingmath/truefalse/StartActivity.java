package com.wordpress.grayfaces.freakingmath.truefalse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.wordpress.grayfaces.freakingmath.R;
import com.wordpress.grayfaces.freakingmath.app.Utility;

public class StartActivity extends AppCompatActivity {
    private View btnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_truefalse);
        Utility.hideNavigationBar(StartActivity.this,getSupportActionBar());
        createView();
    }
    private void createView(){
        btnPlay = findViewById(R.id.truefalse_start_btnPlay);
        setChange();
    }
    private void setChange(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,PlayActivity.class);
                startActivity(intent);
                StartActivity.this.finish();

            }
        });
    }
}
