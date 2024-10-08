package com.example.quizappbsi;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class EndScreen_Win extends AppCompatActivity {


     Button buttonChangeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end_screen_win);

        buttonChangeScreen = findViewById(R.id.btn_finish);

        buttonChangeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndScreen_Win.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}