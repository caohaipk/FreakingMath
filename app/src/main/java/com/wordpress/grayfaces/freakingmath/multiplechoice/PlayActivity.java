package com.wordpress.grayfaces.freakingmath.multiplechoice;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.common.BaseRoundCornerProgressBar;
import com.wordpress.grayfaces.freakingmath.R;
import com.wordpress.grayfaces.freakingmath.app.QuitDialog;
import com.wordpress.grayfaces.freakingmath.app.Utility;
import com.wordpress.grayfaces.freakingmath.ui.BackgroundColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private TextView txtLeftNumber,txtRightNumber, txtScore, txtOperators;
    private List<TextView> listAnswer;
    private View layoutRoot;
    private RoundCornerProgressBar progressBar;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_multiplechoice);
        Utility.hideNavigationBar(getSupportActionBar());
        setView();
    }
    private void setView(){
        txtLeftNumber = (TextView) findViewById(R.id.multiplechoice_play_txtLeftNumber);
        txtRightNumber = (TextView) findViewById(R.id.multiplechoice_play_txtRightNumber);
        txtOperators = (TextView) findViewById(R.id.multiplechoice_play_txtOperator);
        txtScore = (TextView) findViewById(R.id.multiplechoice_play_txtScore);
        layoutRoot = findViewById(R.id.main_layout_root);
        listAnswer = new ArrayList<>();
        listAnswer.add((TextView) findViewById(R.id.multiplechoice_play_txtAnswerA));
        listAnswer.add((TextView) findViewById(R.id.multiplechoice_play_txtAnswerB));
        listAnswer.add((TextView) findViewById(R.id.multiplechoice_play_txtAnswerC));
        listAnswer.add((TextView) findViewById(R.id.multiplechoice_play_txtAnswerD));
        progressBar = (RoundCornerProgressBar) findViewById(R.id.multiplechoice_play_processBar_timerCountDown);
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
        setChange();
        initNewQuestion();
    }
    private void setChange(){
        for (int i = 0; i < listAnswer.size();i++){
            final int finalI = i;
            listAnswer.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int answer = Integer.parseInt(listAnswer.get(finalI).getText().toString());
                    checkTheAnswer(answer);
                }
            });
        }
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
                resultNumber = left + right;
                break;
            case 1:
                txtOperators.setText("-");
                resultNumber = left - right;
                break;
        }

        txtLeftNumber.setText(String.valueOf(left));
        txtRightNumber.setText(String.valueOf(right));
        setAnswer(listAnswer,resultNumber);
        int time = 5 * 1000;
        progressBar.setMax(time);
        progressBar.setProgress(time);
        progressBar.setProgressColor(Color.parseColor("#8BC34A"));
        progressBar.setProgressBackgroundColor(Color.parseColor("#757575"));
        setTimerCountdown(time);
    }
    private void setAnswer(List<TextView> listAnswer,final int result){
        Random random = new Random();
        final int location = random.nextInt(4);
        System.out.println("loca "+location);
        System.out.println(result);
        listAnswer.get(location).setText(String.valueOf(result));
        int left = location-1;
        while (left>=0){
            int rs = Integer.parseInt(listAnswer.get(left+1).getText().toString()) - random.nextInt(2) - 1;
            System.out.println(rs);
            listAnswer.get(left).setText(String.valueOf(rs));
            left--;
        }
        int right = location+1;
        while (right<=3){
            int rs = Integer.parseInt(listAnswer.get(right-1).getText().toString()) + random.nextInt(2) + 1;
            System.out.println(rs);
            listAnswer.get(right).setText(String.valueOf(rs));
            right++;
        }
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
    private void checkTheAnswer(int answer){
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
        if (answer == rightResult){
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
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
