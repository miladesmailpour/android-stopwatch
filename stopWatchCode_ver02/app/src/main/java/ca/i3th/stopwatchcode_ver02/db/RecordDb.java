package ca.i3th.stopwatchcode_ver02.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Record.class}, version = 1, exportSchema = false)
public abstract class RecordDb extends RoomDatabase {
    private static RecordDb instance;

    public abstract RecordDao recordDeo();

    public static synchronized RecordDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RecordDb.class
                    , "records_db").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
