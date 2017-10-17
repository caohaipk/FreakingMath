package com.wordpress.grayfaces.freakingmath;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.common.BaseRoundCornerProgressBar;
import com.wordpress.grayfaces.freakingmath.ui.BackgroundColor;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txtLeftNumber,txtRightNumber,txtResultNumber, txtScore, txtOperators;
    private Button btnTrue,btnFalse;
    private View layoutRoot;
    private RoundCornerProgressBar progressBar;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
    }
    private void setView(){
        txtLeftNumber = (TextView) findViewById(R.id.main_txtLeftNumber);
        txtRightNumber = (TextView) findViewById(R.id.main_txtRightNumber);
        txtOperators = (TextView) findViewById(R.id.main_txtOperator);
        txtResultNumber = (TextView) findViewById(R.id.main_txtResultNumber);
        txtScore = (TextView) findViewById(R.id.main_txtScore);
        layoutRoot = findViewById(R.id.main_layout_root);
        btnTrue = (Button) findViewById(R.id.main_btnTrue);
        btnFalse = (Button) findViewById(R.id.main_btnFalse);
        progressBar = (RoundCornerProgressBar) findViewById(R.id.main_processBar_timerCountDown);
        progressBar.setOnProgressChangedListener(new BaseRoundCornerProgressBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int viewId, float progress, boolean isPrimaryProgress, boolean isSecondaryProgress) {
                int percent = ((int) progress)*100/((int)progressBar.getMax());
                System.out.println(percent);
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
        setTimerCountdown();
    }
    private void setTimerCountdown(){
        int time = 5 * 1000;
        progressBar.setMax(time);
        progressBar.setProgress(time);
        progressBar.setProgressColor(Color.parseColor("#8BC34A"));
        progressBar.setProgressBackgroundColor(Color.parseColor("#757575"));
        countDownTimer = new CountDownTimer(time,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                txtScore.setText("0");
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
            txtScore.setText("0");
        }
        initNewQuestion();
    }
}
