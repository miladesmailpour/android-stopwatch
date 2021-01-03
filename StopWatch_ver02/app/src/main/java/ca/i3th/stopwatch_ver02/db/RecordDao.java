package ca.i3th.stopwatch_ver02.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface RecordDao {

    @Insert
    public void addRecord(Record record);
}
