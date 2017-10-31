package com.wordpress.grayfaces.freakingmath.multiplechoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wordpress.grayfaces.freakingmath.R;
import com.wordpress.grayfaces.freakingmath.app.Utility;

/**
 * Project FreakingMath
 * Created by Gray on 10/24/2017.
 */

public class FinishActivity extends AppCompatActivity {
    private TextView txtScore;
    protected View btnTryAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_truefalse);
        Utility.hideNavigationBar(getSupportActionBar());
        createView();
    }
    private void createView(){
        txtScore = (TextView) findViewById(R.id.truefalse_finish_txtScore);
        btnTryAgain = findViewById(R.id.truefalse_finish_btnTryAgain);
        initScore();
        setChange();
    }
    private void initScore(){
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE",0);
        txtScore.setText(String.valueOf(score));
    }
    private void setChange(){
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this,PlayActivity.class);
                startActivity(intent);
                FinishActivity.this.finish();
            }
        });
    }
}
