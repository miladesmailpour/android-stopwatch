package ca.i3th.stopwatchcode_ver02.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import ca.i3th.stopwatchcode_ver02.AuxFun.FragmentInfo;
import ca.i3th.stopwatchcode_ver02.MainActivity;
import ca.i3th.stopwatchcode_ver02.R;
import ca.i3th.stopwatchcode_ver02.db.Record;
import ca.i3th.stopwatchcode_ver02.db.RecordViewModel;


public class Lap extends Fragment {

    private RecordViewModel recordViewModel;

    private static final String TAG = "fragment_lap";
    private View tmpView;
    private FragmentInfo fragmentInfo;
//    private ViewGroup.LayoutParams params;

    public Lap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lap, container, false);
        tmpView= view;

        recordViewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory.getInstance(((MainActivity)getActivity()).getApp()))
                .get(RecordViewModel.class);
        recordViewModel.getAllRecord().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                Toast.makeText(getActivity(), "onChange", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
//        try {
//            fragmentInfo = new FragmentInfo(tmpView);
//            tmpView.setLayoutParams(fragmentInfo.setFragmentHeightWeight(30, 30));
//        } catch (Exception e) {
//            Log.d(TAG, "2 GroupInfoFragment Height or weight Exception: " + e.toString());
//        }
    }
}