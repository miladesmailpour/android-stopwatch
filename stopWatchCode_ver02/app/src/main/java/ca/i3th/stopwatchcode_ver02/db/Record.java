package ca.i3th.stopwatchcode_ver02.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "records")
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int rid;
    private String record;

    public Record(String record) {
        this.record = record;
    }

    public String getRecord() {
        return record;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}