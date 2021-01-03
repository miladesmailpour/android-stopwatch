package ca.i3th.stopwatch_ver02.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class}, version = 1)
public abstract class DBRecord extends RoomDatabase {
    public abstract RecordDao recordDao();
}
