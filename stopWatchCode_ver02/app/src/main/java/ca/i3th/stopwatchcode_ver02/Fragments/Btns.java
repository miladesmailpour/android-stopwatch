package ca.i3th.stopwatchcode_ver02.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import ca.i3th.stopwatchcode_ver02.MainActivity;
import ca.i3th.stopwatchcode_ver02.R;
import ca.i3th.stopwatchcode_ver02.db.Record;

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
                ((MainActivity) getActivity()).getCircles().runPointers();//////////////
                btnSound();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 2;
                setBtn();
                ((MainActivity) getActivity()).getCircles().stopPointers();
                ((MainActivity) getActivity()).getStopwatch().stopTime();
//                ((MainActivity) getActivity()).openDialog(((MainActivity) getActivity()).getStopwatch().recordList());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flag = ((MainActivity) getActivity()).getStopwatch().currentRecord();
                if (!flag.equals("-1")) {
                    ((MainActivity) getActivity()).getRecordViewModel().insert(new Record(flag));

                    btnSave.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
                }
            }
        });
        return view;
    }

    public void setBtn() {
        if (flag == 1) {
            btnPlayPause.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.btn_button_pause_150_foreground, null));
            flag = 2;
        } else if (flag == 2) {
            btnPlayPause.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                    R.drawable.btn_button_play_150_foreground, null));

            flag = 1;
        } else {
            flag = 0;
        }
    }

    public int fl_handler(int f) {
        if (f != 1 || f != 2) {
            setBtn();
            ((MainActivity) getActivity()).getStopwatch().startTime();
        } else {
            flag = 0;
            return -1;
        }
        return flag;
    }

    private void btnSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(((MainActivity)getActivity()).getApp(), R.raw.beep23);
        mediaPlayer.start();
    }

    public int getFlag() {
        return flag;
    }
}