package ca.i3th.stopwatchcode_ver02.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    private RecordRepository repository;
    private LiveData<List<Record>> allRecord;
    public RecordViewModel(@NonNull Application application) {
        super(application);
    }
    public void insert(Record record) {
        repository.insert(record);
    }
    public void update(Record record) {
        repository.update(record);
    }
    public void delete(Record record) {
        repository.delete(record);
    }
    public void deleteAll() {
        repository.deleteAll();
    }
    public LiveData<List<Record>> getAllRecord() {
        return allRecord;
    }
}
