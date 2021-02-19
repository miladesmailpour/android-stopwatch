package ca.i3th.stopwatchcode_ver02.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDao {

    @Insert
    void insert(Record record);

    @Update
    void update(Record record);

    @Delete
    void delete(Record records);

    @Query("DELETE FROM records")
    void deleteAll();

    @Query("SELECT * FROM records ORDER BY rid DESC")
    LiveData<List<Record>> getAllRecords();
}
