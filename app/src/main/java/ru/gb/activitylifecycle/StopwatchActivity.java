package ru.gb.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    private TextView stopwatchTimer;
    private Button stopwatchStart;
    private Button stopwatchStop;
    private Button stopwatchReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
        workButtons();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    private void workButtons() {
        stopwatchStart = findViewById(R.id.button_start);
        stopwatchStop = findViewById(R.id.button_stop);
        stopwatchReset = findViewById(R.id.button_reset);

        stopwatchStart.setOnClickListener(v -> running = true);

        stopwatchStop.setOnClickListener(v -> running = false);

        stopwatchReset.setOnClickListener(v -> {
            running = false;
            seconds = 0;
        });
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(wasRunning){
//            running = true;
//        }
//    }

@Override
protected void onResume() {
    super.onResume();
    if(wasRunning){
        running = true;
    }
}

//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasRunning = running;
//        running = false;
//    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    private void runTimer() {
        stopwatchTimer = findViewById(R.id.time_view);
        Handler handler = new Handler();        //создание объекта Handler, который увеличивает число секунда в TextView
        handler.post(new Runnable() {           //вызов метода post(), в котором переопределяется метод Runnable(), в котором указываются единицы измерения + форматированный вывод самой строки TextView
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);  //форматированный вывод самой строки TextView
                stopwatchTimer.setText(time);  //строка time, в которую передаются значения форматированного вывода
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);    //задержка, с которой обновляется форматированнная стролка
            }
        });
    }
}