package com.wordpress.grayfaces.freakingmath.truefalse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.common.BaseRoundCornerProgressBar;
import com.wordpress.grayfaces.freakingmath.R;
import com.wordpress.grayfaces.freakingmath.app.QuitDialog;
import com.wordpress.grayfaces.freakingmath.app.Utility;
import com.wordpress.grayfaces.freakingmath.ui.BackgroundColor;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private TextView txtLeftNumber,txtRightNumber,txtResultNumber, txtScore, txtOperators;
    private View btnTrue,btnFalse;
    private View layoutRoot;
    private RoundCornerProgressBar progressBar;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_truefalse);
        Utility.hideNavigationBar(getSupportActionBar());
        setView();
    }
    private void setView(){
        txtLeftNumber = (TextView) findViewById(R.id.truefalse_play_txtLeftNumber);
        txtRightNumber = (TextView) findViewById(R.id.truefalse_play_txtRightNumber);
        txtOperators = (TextView) findViewById(R.id.truefalse_play_txtOperator);
        txtResultNumber = (TextView) findViewById(R.id.truefalse_play_txtResultNumber);
        txtScore = (TextView) findViewById(R.id.truefalse_play_txtScore);
        layoutRoot = findViewById(R.id.main_layout_root);
        btnTrue = findViewById(R.id.truefalse_play_btnTrue);
        btnFalse = findViewById(R.id.truefalse_play_btnFalse);
        progressBar = (RoundCornerProgressBar) findViewById(R.id.truefalse_play_processBar_timerCountDown);
        progressBar.setOnProgressChangedListener(new BaseRoundCornerProgressBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int viewId, float progress, boolean isPrimaryProgress, boolean isSecondaryProgress) {
                int percent = ((int) progress)*100/((int)progressBar.getMax());
                if(percent<=50){
                    if (percent<=30){
                        progressBar.setProgressColor(Color.parseColor("#f44336"));
                    } else {
                        progressBar.setProgressColor(Color.parseColor("#FF9800"));
                    }
                }
            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTheAnswer(true);
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTheAnswer(false);
            }
        });
        initNewQuestion();
    }
    private void initBackgroundColor(View backGround){
        BackgroundColor backgroundColor = new BackgroundColor();
        backGround.setBackgroundColor(Color.parseColor(backgroundColor.getRandomColor()));
    }
    private void initNewQuestion(){
        if (Integer.parseInt(txtScore.getText().toString()) % 10 == 0 ) {
            initBackgroundColor(layoutRoot);
        }
        Random random = new Random();
        int left = random.nextInt(20) + 1;
        int right = random.nextInt(20) + 1;
        //20 is the maximum and the 1 is our minimum
        int operator = random.nextInt(2);
        int resultNumber = 0;
        switch (operator){
            case 0:
                txtOperators.setText("+");
                resultNumber = left + right + random.nextInt(2);
                break;
            case 1:
                txtOperators.setText("-");
                resultNumber = left - right + random.nextInt(2);
                break;
        }

        txtLeftNumber.setText(String.valueOf(left));
        txtRightNumber.setText(String.valueOf(right));
        txtResultNumber.setText(String.valueOf(resultNumber));
        int time = 5 * 1000;
        progressBar.setMax(time);
        progressBar.setProgress(time);
        progressBar.setProgressColor(Color.parseColor("#8BC34A"));
        progressBar.setProgressBackgroundColor(Color.parseColor("#757575"));
        setTimerCountdown(time);
    }
    private void setTimerCountdown(int time){
        countDownTimer = new CountDownTimer(time,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(millisUntilFinished);
            }

            @Override
            public void onFinish() {
//                progressBar.setProgress(0);
//                txtScore.setText("0");
//                initNewQuestion();
                finishGame();
            }
        };
        countDownTimer.start();
    }
    private void checkTheAnswer(boolean answer){
        countDownTimer.cancel();
        countDownTimer = null;
        int left = Integer.parseInt(txtLeftNumber.getText().toString());
        int right = Integer.parseInt(txtRightNumber.getText().toString());
        int rightResult = 0;
        switch (txtOperators.getText().toString()){
            case "+":
                rightResult = left + right;
                break;
            case "-":
                rightResult = left - right;
                break;
        }
        int result = Integer.parseInt(txtResultNumber.getText().toString());
        boolean resultAnswer = (result == rightResult);
        if (answer == resultAnswer){
            int score = Integer.parseInt(txtScore.getText().toString()) + 1;
            txtScore.setText(String.valueOf(score));
        } else {
            //txtScore.setText("0");
            finishGame();
        }
        initNewQuestion();
    }
    private void finishGame(){
        int score = Integer.parseInt(txtScore.getText().toString());
        Intent intent = new Intent(PlayActivity.this,FinishActivity.class);
        intent.putExtra("SCORE",score);
        startActivity(intent);
        this.finish();
    }
    private void resumeCountDownTimer(CountDownTimer timer, RoundCornerProgressBar progress, int maxTime){
        int time = maxTime * (int)progress.getProgress() / (int)progress.getMax();
        setTimerCountdown(time);
    }
    @Override
    public void onBackPressed(){
        final QuitDialog quitDialog = new QuitDialog();
        quitDialog.setMenuVisibility(true);
        quitDialog.setOnDialogFrmCilckListener(new QuitDialog.OnDialogFragmentClickListener() {
            @Override
            public void onOkClicked(QuitDialog dialog) {
                finishGame();
            }

            @Override
            public void onCancelClicked(QuitDialog dialog) {
                quitDialog.dismiss();
                //resumeCountDownTimer(countDownTimer,progressBar,5*1000);
                initNewQuestion();
            }
        });
        countDownTimer.cancel();
        quitDialog.show(getFragmentManager(),"");
        //super.onBackPressed();
    }
}
