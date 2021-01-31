package ca.i3th.stopwatchcode_ver02.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.xml.transform.Result;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<Record>> allRecords;

    public RecordRepository(Application application) {
        RecordDb db = RecordDb.getInstance(application);
        recordDao = db.recordDeo();
        allRecords = recordDao.getAllRecords();
    }

    public void insert(Record record) {
        new InsertRecordAsyncTask(recordDao).execute(record);
    }

    public void update(Record record) {
        new UpdateRecordAsyncTask(recordDao).execute(record);
    }

    public void delete(Record record) {
        new DeleteRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAll() {
        new DeleteAllRecordAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllRecords() {
        return allRecords;
    }

    private static class InsertRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao recordDao;

        private InsertRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insert(records[0]);
            return null;
        }
    }
    private static class UpdateRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao recordDao;

        private UpdateRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.update(records[0]);
            return null;
        }
    }
    private static class DeleteRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao recordDao;

        private DeleteRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.delete(records[0]);
            return null;
        }
    }
    private static class DeleteAllRecordAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecordDao recordDao;

        private DeleteAllRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAll();
            return null;
        }
    }
}
