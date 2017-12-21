package com.ocularrhythm.eggtimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String time = String.format(Locale.ENGLISH, "%d:%02d", minutes, seconds);

        timerTextView.setText(time);
    }

    public void controlTimer(View view) {

        new CountDownTimer(timerSeekBar.getProgress() *  1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(1000);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
