package ca.i3th.stopwatchcode_ver02.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.i3th.stopwatchcode_ver02.MainActivity;
import ca.i3th.stopwatchcode_ver02.R;
import ca.i3th.stopwatchcode_ver02.db.Record;

import static ca.i3th.stopwatchcode_ver02.R.color.androidx_core_ripple_material_light;
import static ca.i3th.stopwatchcode_ver02.R.color.white;


public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<Record> records = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        context = view.getContext();
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        Record currRecord = records.get(position);
//        holder.rid.setText(String.valueOf(currRecord.getRid()));
        if(!(position == 0))
        {
            holder.record.setTextColor(0x99FFBB33);
        }
        else
        {
            holder.record.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
            holder.record.setTextColor(0xFFFFBB33);
        }
//        holder.record.setTextColor(0xFFFFBB33);
        holder.record.setText(currRecord.getRecord());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<Record> records) {
        this.records = records;
        notifyDataSetChanged();
    }
    class RecordHolder extends RecyclerView.ViewHolder {
        private TextView rid;
        private TextView record;
        public RecordHolder(View view) {
            super(view);
//            rid = view.findViewById(R.id.tv_rid);
            record = view.findViewById(R.id.tv_record);
        }
    }
}
