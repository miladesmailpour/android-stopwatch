package ca.i3th.stopwatchcode_ver02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.Toast;


import java.util.HashMap;
import java.util.List;

import ca.i3th.stopwatchcode_ver02.AuxFun.RecordList;
import ca.i3th.stopwatchcode_ver02.AuxFun.ScreenInfo;
import ca.i3th.stopwatchcode_ver02.AuxFun.StopWatchManager;
import ca.i3th.stopwatchcode_ver02.Fragments.Btns;
import ca.i3th.stopwatchcode_ver02.Fragments.Circles;
import ca.i3th.stopwatchcode_ver02.Fragments.Lap;
import ca.i3th.stopwatchcode_ver02.databinding.ActivityMainBinding;
import ca.i3th.stopwatchcode_ver02.db.Record;
import ca.i3th.stopwatchcode_ver02.db.RecordViewModel;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private ScreenInfo screenInfo;
    private StopWatchManager stopwatch;
    private Handler handler;
    private Chronometer chronometer;
    private Lap lap;
    private Circles circles;
    private Btns btns;
    private FrameLayout fl_mainContainer_middle;
    private Application app;
    private RecordViewModel recordViewModel;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        String h = "";
        app = this.getApplication();


        circles = new Circles();
        btns = new Btns();
        lap = new Lap();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.mainContainer_top, lap)
                    .add(R.id.mainContainer_middle, circles)
                    .add(R.id.mainContainer_bottom, btns)
                    .setReorderingAllowed(true)
                    .commit();
        }
        Log.d(TAG, "onCreate: +++++++++++++++++++++++++++++++++++++++>");
        fl_mainContainer_middle = findViewById(R.id.mainContainer_middle);

//        screenInfo = new ScreenInfo(new DisplayMetrics(), this);
//        Toast.makeText(this, screenInfo.size() + " : " + screenInfo.screenLayout(), Toast.LENGTH_LONG).show();
//        Log.d(TAG, "onCreate: -------------------------------> : " + screenInfo.size()
//                + " : " + screenInfo.screenLayout() + " : " + screenInfo.density()
//                + " : " + screenInfo.sizePicker());

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: +++++++++++++++++++++++++++++++++++++++>");
//        setContentView(R.layout.activity_main);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        String h = "";
//        app = this.getApplication();
//
//
//        circles = new Circles();
//        btns = new Btns();
//        lap = new Lap();
//        if (bundle == null) {
//            fragmentManager.beginTransaction()
//                    .add(R.id.mainContainer_top, lap)
//                    .add(R.id.mainContainer_middle, circles)
//                    .add(R.id.mainContainer_bottom, btns)
//                    .setReorderingAllowed(true)
//                    .commit();
//        }
//        Log.d(TAG, "onCreate: +++++++++++++++++++++++++++++++++++++++>");
//        fl_mainContainer_middle = findViewById(R.id.mainContainer_middle);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: +++++++++++++++++++++++++++++++++++++++>");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: +++++++++++++++++++++++++++++++++++++++>");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: +++++++++++++++++++++++++++++++++++++++>");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: +++++++++++++++++++++++++++++++++++++++>");

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: +++++++++++++++++++++++++++++++++++++++>");
        super.onResume();
        chronometer = circles.getChronometer();
        handler = new Handler();
        stopwatch = new StopWatchManager(chronometer, handler);
        fl_mainContainer_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btns.fl_handler(btns.getFlag());
            }
        });

    }

    public static HashMap<String, Object> screenInfo(int option, Activity activity) {
        String[] op = {"0 : ScreenInfoGuid", "1 : (double)Screen Size", "2 : (char)Screen Layout", "3 : (int)Screen Density", "4 : (int)Screen Picker"};
        HashMap<String, Object> result = new HashMap<>();
        ScreenInfo screenInfo = new ScreenInfo(new DisplayMetrics(), activity);
        switch (option) {
            case 1:
                result.put("Screen Size", screenInfo.screenLayout());
                break;
            case 2:
                result.put("Screen Layout", screenInfo.screenLayout());
                break;
            case 3:
                result.put("Screen Density", screenInfo.density());
                break;
            case 4:
                result.put("Screen SizePicker", screenInfo.sizePicker());
                break;
            default:
                result.put("List of options", op);
        }
        return result;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    private void restRecords() {
        if (!getStopwatch().getIsActive()) {
            getStopwatch().stopTime();
            getCircles().stopPointers();
            recordViewModel.deleteAll();
        }
        else {
            Toast.makeText(MainActivity.this, "STOP the time if you wish to Rest data",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_rest:
                restRecords();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Circles getCircles() {
        return circles;
    }

    public String setContextRL() {
        return stopwatch.recordList();
    }

    public StopWatchManager getStopwatch() {
        return stopwatch;
    }

    public Application getApp() { return app; }

    public RecordViewModel getRecordViewModel() { return recordViewModel; }

    public void setRecordViewModel(RecordViewModel rvm) { recordViewModel = rvm; }

}