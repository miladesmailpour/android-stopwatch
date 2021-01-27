package ca.i3th.stopwatchcode_ver02.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import ca.i3th.stopwatchcode_ver02.AuxFun.CircleAnimate;
import ca.i3th.stopwatchcode_ver02.R;

public class Circles extends Fragment {

    private static final String TAG = "Circles";
    private ImageView ivSecond, ivMinute, ivHour;
    private int sec, min, hour = 0;
    private Chronometer chronometer;
    private CircleAnimate circleAnimateSec, circleAnimateMin, circleAnimateHour;
    private Thread thread;


    public Circles() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circles, container, false);

        this.chronometer = view.findViewById(R.id.chronometer_main);


        ivSecond = (ImageView) view.findViewById(R.id.iv_pointer_second_foreground);
        ivMinute = (ImageView) view.findViewById(R.id.iv_pointer_minute_foreground);
        ivHour = (ImageView) view.findViewById(R.id.iv_pointer_hour_foreground);
        circleAnimateSec = new CircleAnimate(ivSecond);
        circleAnimateMin = new CircleAnimate(ivMinute);
        circleAnimateHour = new CircleAnimate(ivHour);



        return view;
    }


    public void runPointers() {

        switch (Boolean.toString(circleAnimateSec.getFlag())) {
            case "true" :
                circleAnimateSec.setFlag(false);
                circleAnimateSec.startAnimate();
                break;
            case "false" :
                circleAnimateSec.setFlag(true);
                circleAnimateSec.pauseAnimate();
                break;
        }

    }

    public boolean stopPointers() {
        boolean pointer = false;
        circleAnimateSec.stopAnimate();
        return pointer;
    }

    public Chronometer getChronometer() {
        return this.chronometer;
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

}

