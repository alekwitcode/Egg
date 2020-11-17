package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar;
        TextView timer;
        final TextView[] text = new TextView[1];
        Button button;
        Button reset;
        final CountDownTimer[] countDownTimer = new CountDownTimer[1];

        reset = findViewById(R.id.reset);

        text[0] = findViewById(R.id.text);
        text[0].setGravity(Gravity.CENTER);
        String startText = "Start by dragging the slider and clicking the button!";
        text[0].setText(startText);

        timer = findViewById(R.id.timer);
        timer.setGravity(Gravity.CENTER);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(14);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text[0] = findViewById(R.id.text);
                String time = String.format(Locale.getDefault(),"%02d:00", progress);

                switch (progress) {
                    case 0:
                        text[0].setText("It be liquid");
                        timer.setText(time);
                        break;
                    case 1:
                    case 2:
                    case 3: {
                        text[0].setText("A jelly-egg");
                        timer.setText(time);
                        break;
                    }
                    case 4:
                    case 5:
                    case 6: {
                        text[0].setText("That's moist and tasty");
                        timer.setText(time);
                        break;
                    }
                    case 7:
                    case 8:
                    case 9: {
                        text[0].setText("I'd say its well-done");
                        timer.setText(time);
                        break;
                    }
                    case 10:
                    case 12: {
                        text[0].setText("I see you like to eat sawdust");
                        timer.setText(time);
                        break;
                    }
                    case 13: text[0].setText("Stop.");
                        timer.setText(time);
                        break;
                    case 14: text[0].setText("Why?");
                        timer.setText(time);
                        break;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {

            int progressInMs = seekBar.getProgress()*60*1000;
            seekBar.setEnabled(false);
            button.setEnabled(false);
            reset.setVisibility(View.VISIBLE);

            countDownTimer[0] = new CountDownTimer(progressInMs, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    int min = (int) millisUntilFinished / 1000 / 60;
                    int sec = (int) millisUntilFinished / 1000 % 60;

                    String time = String.format(Locale.getDefault(), "%02d:%02d", min, sec);

                    timer.setText(time);
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    text[0].setText("It's done!");
                }
            }.start();
        });

        reset.setOnClickListener(v -> {
            seekBar.setEnabled(true);
            seekBar.setProgress(0);
            button.setEnabled(true);
            countDownTimer[0].cancel();
            text[0].setText(startText);
            timer.setText("00:00");
            reset.setVisibility(View.INVISIBLE);
        });
    }
}