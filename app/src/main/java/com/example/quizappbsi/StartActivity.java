package com.example.quizappbsi;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.startButton) {
                    startCountdown();
                }
            }

            private void startCountdown() {
                final TextView countdownTextView = findViewById(R.id.countdownTextView);
                final Button startButton = findViewById(R.id.startButton);

                countdownTextView.setVisibility(View.VISIBLE);
                startButton.setEnabled(false);

                new CountDownTimer(4000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        int secondsLeft = (int) (millisUntilFinished / 1000);
                        if (secondsLeft >= 1) {
                            countdownTextView.setText(String.valueOf(secondsLeft));
                        } else if (secondsLeft < 1) {
                            countdownTextView.setText("GO!");
                        }
                        countdownTextView.startAnimation(AnimationUtils.loadAnimation(StartActivity.this, R.anim.countdown_animation));
                    }

                    public void onFinish() {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Optional, um die StartActivity zu schließen
                    }
                }.start();
            }
        });
    }

}
