package ca.i3th.stopwatchcode_ver02.db;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RecordDao_Impl implements RecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Record> __insertionAdapterOfRecord;

  private final EntityDeletionOrUpdateAdapter<Record> __deletionAdapterOfRecord;

  private final EntityDeletionOrUpdateAdapter<Record> __updateAdapterOfRecord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public RecordDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecord = new EntityInsertionAdapter<Record>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `records` (`rid`,`record`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Record value) {
        stmt.bindLong(1, value.getRid());
        if (value.getRecord() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRecord());
        }
      }
    };
    this.__deletionAdapterOfRecord = new EntityDeletionOrUpdateAdapter<Record>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `records` WHERE `rid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Record value) {
        stmt.bindLong(1, value.getRid());
      }
    };
    this.__updateAdapterOfRecord = new EntityDeletionOrUpdateAdapter<Record>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `records` SET `rid` = ?,`record` = ? WHERE `rid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Record value) {
        stmt.bindLong(1, value.getRid());
        if (value.getRecord() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRecord());
        }
        stmt.bindLong(3, value.getRid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM records";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Record record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRecord.insert(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Record records) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRecord.handle(records);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Record record) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRecord.handle(record);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Record>> getAllRecords() {
    final String _sql = "SELECT * FROM records ORDER BY rid DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"records"}, false, new Callable<List<Record>>() {
      @Override
      public List<Record> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRid = CursorUtil.getColumnIndexOrThrow(_cursor, "rid");
          final int _cursorIndexOfRecord = CursorUtil.getColumnIndexOrThrow(_cursor, "record");
          final List<Record> _result = new ArrayList<Record>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Record _item;
            final String _tmpRecord;
            _tmpRecord = _cursor.getString(_cursorIndexOfRecord);
            _item = new Record(_tmpRecord);
            final int _tmpRid;
            _tmpRid = _cursor.getInt(_cursorIndexOfRid);
            _item.setRid(_tmpRid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
