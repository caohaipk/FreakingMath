package com.wordpress.grayfaces.freakingmath.truefalse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wordpress.grayfaces.freakingmath.R;
import com.wordpress.grayfaces.freakingmath.app.Utility;

public class FinishActivity extends AppCompatActivity {
    private TextView txtScore;
    private View btnTryAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_truefalse);
        Utility.hideNavigationBar(FinishActivity.this, getSupportActionBar());
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
