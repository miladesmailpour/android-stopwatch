package ca.i3th.stopwatchcode_ver02.AuxFun;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;

import ca.i3th.stopwatchcode_ver02.MainActivity;
import ca.i3th.stopwatchcode_ver02.R;

public class RecordList extends DialogFragment {

    private static final String TAG = "RL";
    private TextView contextMag;
    private Button closeList;
    private LinearLayoutCompat linearLayoutCompat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.records_list, container, false);


        String rec = ((MainActivity)getActivity()).setContextRL();

        contextMag = (TextView)view.findViewById(R.id.contextRL);

        if (rec == null || rec.isEmpty()) {
            contextMag.setText("Empty!");
        }
        else{
            contextMag.setText(rec);
        }
        closeList = view.findViewById(R.id.btnRL);

        closeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Last 10 records ONLY display!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        return view;
    }
}

