package ca.i3th.stopwatchcode_ver02.Fragments;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
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
    private ImageView ivSecond, ivMinute, ivHour, ivQ1, ivQ2, ivQ3, ivQ4;
    private ImageView[] qList;
    private int sec, min, hour = 0;
    private Chronometer chronometer;
    private CircleAnimate circleAnimateSec, circleAnimateMin, circleAnimateHour;



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
        this.chronometer.setTypeface(ResourcesCompat.getFont(getContext(), R.font.digital_7));
        ivQ1 = (ImageView) view.findViewById(R.id.iv_quarter_1_foreground);
        ivQ2 = (ImageView) view.findViewById(R.id.iv_quarter_2_foreground);
        ivQ3 = (ImageView) view.findViewById(R.id.iv_quarter_3_foreground);
        ivQ4 = (ImageView) view.findViewById(R.id.iv_quarter_4_foreground);
        qList = new ImageView[]{ivQ1, ivQ2, ivQ3, ivQ4};
//        for (int i = 0; i < qList.length; i++) {
//            qList[i].setAlpha(0.0f);
//        }
        ivSecond = (ImageView) view.findViewById(R.id.iv_pointer_second_foreground);
        ivMinute = (ImageView) view.findViewById(R.id.iv_pointer_minute_foreground);
        ivHour = (ImageView) view.findViewById(R.id.iv_pointer_hour_foreground);
        circleAnimateSec = new CircleAnimate(ivSecond, 's', qList);
        circleAnimateMin = new CircleAnimate(ivMinute, 'm', qList);
        circleAnimateHour = new CircleAnimate(ivHour, 'h', qList);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void runPointers() {

        switch (Boolean.toString(circleAnimateSec.getFlag())) {
            case "true" :
                circleAnimateSec.setFlag(false);
                circleAnimateSec.startAnimate();

                circleAnimateMin.setFlag(false);
                circleAnimateMin.startAnimate();

                circleAnimateHour.setFlag(false);
                circleAnimateHour.startAnimate();
                break;

            case "false" :
                circleAnimateSec.setFlag(true);
                circleAnimateSec.pauseAnimate();

                circleAnimateMin.setFlag(true);
                circleAnimateMin.pauseAnimate();

                circleAnimateHour.setFlag(true);
                circleAnimateHour.pauseAnimate();
                break;
        }

    }

    public boolean stopPointers() {
//        boolean pointer = false;
        circleAnimateSec.stopAnimate();
        circleAnimateMin.stopAnimate();
        circleAnimateHour.stopAnimate();
        return true;
    }

    public Chronometer getChronometer() {
        return this.chronometer;
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }


}

