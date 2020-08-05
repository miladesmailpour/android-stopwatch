package ca.i3th.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    ImageButton startBtn, stopBtn, saveBtn, recListBtn;
    List<String> list;

    Handler handler;
    private Stopwatch stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        startBtn = findViewById(R.id.btnStart);
        stopBtn = findViewById(R.id.btnStop);
        saveBtn = findViewById(R.id.btnSave);
        recListBtn = findViewById(R.id.btnRecList);

        list = new ArrayList<String>();

        handler = new Handler();

        stopwatch = new Stopwatch(chronometer,handler);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn(1);
                stopwatch.startTime();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn(2);
                stopwatch.stopTime();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatch.saveRecord();
            }
        });

        recListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatch.recordList();
            }
        });
    }

    private void setBtn(int option) {
        if (option == 1)
            startBtn.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.ic_baseline_pause_circle_filled_24, null));
        if (option == 2)
            startBtn.setImageDrawable(ResourcesCompat.getDrawable(
                    getResources(), R.drawable.ic_baseline_play_circle_filled_40, null));
    }
}