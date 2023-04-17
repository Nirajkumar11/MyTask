package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText text;
    int count = 0;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.IdEdtTv);
        progressBar = findViewById(R.id.IdProgressBar);


        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String EnterText = text.getText().toString();
                int value = Integer.parseInt(EnterText.equalsIgnoreCase("") ? "0" : EnterText);
                if (value <= 0) {
                    count=0;
                    progressBar.setProgress(count);
                    progressBar.setMax(100);
                    return;
                }

                if (count > 0) {
                    timer.cancel();
                    timer.purge();
                    count = 0;
                    progressBar.setProgress(count);
                    progressBar.setMax(100);
                }
                timer = new Timer();

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        count++;
                        progressBar.setProgress(count);
                        if (count == value) {
//                            int counter = Integer.parseInt(EnterText.equalsIgnoreCase("") ? "0" : EnterText);
                            progressBar.setProgress(value);
                            progressBar.setMax(100);
                            timer.cancel();
                        }

                    }
                };

                timer.schedule(timerTask, 0, 100);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}

