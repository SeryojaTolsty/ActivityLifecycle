package ru.gb.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        stopwatchTimer = findViewById(R.id.time_view);
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
}