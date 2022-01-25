package ru.gb.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    private TextView stopwatchTimer;
    private Button stopwatchStart;
    private Button stopwatchStop;
    private Button stopwatchReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer();

        stopwatchStart = findViewById(R.id.button_start);
        stopwatchStop = findViewById(R.id.button_stop);
        stopwatchReset = findViewById(R.id.button_reset);

        stopwatchStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });

        stopwatchStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        stopwatchReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });
    }

    private void runTimer(){
        stopwatchTimer = findViewById(R.id.time_view);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",hours, minutes, secs);
                stopwatchTimer.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}