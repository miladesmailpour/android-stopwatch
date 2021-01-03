package ca.i3th.stopwatch_ver02.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "records")
public class Record {

    @PrimaryKey
    private int id;
    private String date;
    private String time;
    private String recordedTime;
    private String details;

    public Record(int id, String date, String time, String recordedTime, String details) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.recordedTime = recordedTime;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(String recordedTime) {
        this.recordedTime = recordedTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
