package ca.i3th.stopwatchcode_ver02.Fragments;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import ca.i3th.stopwatchcode_ver02.MainActivity;
import ca.i3th.stopwatchcode_ver02.R;

public class Btns extends Fragment {

    private String TAG = "MainActivity";
    private ImageButton btnPlayPause, btnPlay, btnStop, btnSave;
    private int flag = 1;

    public Btns() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_btns, container, false);
        btnPlayPause = view.findViewById(R.id.btnStart);
        btnPlay = view.findViewById(R.id.btnStart);
        btnStop = view.findViewById(R.id.btnStop);
        btnSave = view.findViewById(R.id.btnSave);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn();
                ((MainActivity) getActivity()).getStopwatch().startTime();
                Log.d(TAG, "onClick: start_cm() -------------------->");
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                setBtn();
                ((MainActivity) getActivity()).getStopwatch().stopTime();
                Log.d(TAG, "onClick: stop_cm() -------------------->");
            }
        });
        return view;
    }

    private void setBtn() {
        if (flag == 1) {
            btnPlayPause.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.btn_button_play_150_foreground, null));
            flag = 2;
        } else {
            btnPlayPause.setImageDrawable(ResourcesCompat.getDrawable(
                    getResources(), R.drawable.btn_button_pause_150_foreground, null));
            flag = 1;
        }
    }

    public void save_cm() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtn();
//                stopwatch.startTime();
            }
        });
    }
}