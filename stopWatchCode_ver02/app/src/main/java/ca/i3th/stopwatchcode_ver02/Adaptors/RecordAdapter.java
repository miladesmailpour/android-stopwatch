package ca.i3th.stopwatchcode_ver02.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.i3th.stopwatchcode_ver02.R;
import ca.i3th.stopwatchcode_ver02.db.Record;


public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<Record> records = new ArrayList<>();

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        Record currRecord = records.get(position);
//        holder.rid.setText(String.valueOf(currRecord.getRid()));
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
