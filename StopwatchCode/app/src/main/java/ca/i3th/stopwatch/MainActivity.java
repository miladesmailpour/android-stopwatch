package ca.i3th.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private TextView lr;
    private ImageButton startBtn, stopBtn, saveBtn, recListBtn;
    private Handler handler;
    private Stopwatch stopwatch;
    private AlertDialog alertDialog;
    private AnimationDrawable animateRoundBlue, animationDrawable;
    private boolean checkAnimate = false;
    private LinearLayoutCompat listV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayoutCompat linearLayoutCompat = findViewById(R.id.main_layout);
        animationDrawable = (AnimationDrawable) linearLayoutCompat.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        LinearLayoutCompat roundLinear = findViewById(R.id.round_area);
        animateRoundBlue = (AnimationDrawable) roundLinear.getBackground();
        animateRoundBlue.setEnterFadeDuration(250);
        animateRoundBlue.setExitFadeDuration(500);

        listV = findViewById(R.id.rl_root);


        chronometer = findViewById(R.id.chronometer);
        startBtn = findViewById(R.id.btnStart);
        stopBtn = findViewById(R.id.btnStop);
        saveBtn = findViewById(R.id.btnSave);
        recListBtn = findViewById(R.id.btnRecList);
        lr = findViewById(R.id.lastRecord);

        handler = new Handler();

        stopwatch = new Stopwatch(chronometer,handler);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkAnimate) {
                    animateRoundBlue.start();
                    animationDrawable.stop();
                    checkAnimate = true;
            }
                else {
                    animateRoundBlue.stop();
                    animationDrawable.start();
                    checkAnimate = false;
                }
                setBtn(1);
                stopwatch.startTime();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRoundBlue.stop();
                checkAnimate = false;
                setBtn(2);
                stopwatch.stopTime();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lr.setText(stopwatch.saveRecord());
                lr.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            }
        });

        recListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(stopwatch.recordList());
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


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    public void openDialog(String list) {
        RL rl = new RL();
        rl.show(getSupportFragmentManager(), "RL");
    }
    public String setContextRL() {
        return stopwatch.recordList();
    }
}