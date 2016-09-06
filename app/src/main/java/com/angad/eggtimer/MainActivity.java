package com.angad.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
     TextView timerTextView;// final is the variable that we create which cannot be changed.
    boolean countIsActive = false;
    Button controllerButton;
    SeekBar timeSeekBar;
    CountDownTimer countDownTimer;
    String a = "Go!";
    String b = "0:30";
    String d = "Stop";
    String e = ":";
    int minutes;
    int seconds;
    String secondString;


    public  void resetTimer(){

        timerTextView.setText(b);
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText(a);
        timeSeekBar.setEnabled(true);

    }


    public  void updateTimer(int secondsLeft){

         minutes = secondsLeft /60;
         seconds = secondsLeft - (minutes*60);

         secondString  = Integer.toString(seconds);


        if(seconds<=9){
            secondString = "0"+ secondString;

         }
        String g =  Integer.toString(minutes) +e+ secondString;
        timerTextView.setText(g);
    }

    public  void controlTimer(View view) {

        if(!countIsActive) {

            countIsActive = true;
            timerSeekBar.setEnabled(false);// this will not allow user to scroll the seek bar when timer is on.
            controllerButton.setText(d);

           countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);// 1000 to get number of seconds.

                }

                @Override
                public void onFinish() {
                    resetTimer();

                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();

                }
            }.start();
        }else{

            resetTimer();

        }

    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        controllerButton =(Button)findViewById(R.id.controllerButton);

        timeSeekBar.setMax(600);// set max time to 600 sec = 10 min
        timeSeekBar.setProgress(30);// start timer from 30 sec

        // linking label of 30 sec to seekbar.
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);





              }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });







    }
}
