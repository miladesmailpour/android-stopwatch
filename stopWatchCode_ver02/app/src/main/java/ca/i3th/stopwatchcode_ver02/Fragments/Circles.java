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
import android.widget.ImageView;

import ca.i3th.stopwatchcode_ver02.AuxFun.CircleAnimate;
import ca.i3th.stopwatchcode_ver02.R;


public class Circles extends Fragment {

    private ImageView ivLine;

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
//        Button rotateBtn = view.findViewById(R.id.btnTest);
//        ivLine = (ImageView) view.findViewById(R.id.imageView);
//        ImageView iv = (ImageView) view.findViewById(R.id.imageView2);
//        CircleAnimate circleAnimate = new CircleAnimate(ivLine);
//        CircleAnimate circleAnimate1 = new CircleAnimate(iv);
//
//        circleAnimate1.startAnimate();
//        circleAnimate.startAnimate();
//        rotateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (Boolean.toString(circleAnimate.getFlag())) {
//                    case "true":
//                        circleAnimate.setFlag(false);
//                        circleAnimate.resumeAnimate();
//                        break;
//                    case "false":
//                        circleAnimate.setFlag(true);
//                        break;
//                }
//            }
//        });
        return view;
    }
}