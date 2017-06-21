package org.lvt.quickscanner.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class DataHelper extends SQLiteOpenHelper {

    private static final String create_table = "CREATE TABLE "+RecordEntity.table+" (" +
            RecordEntity._id+" INTEGER PRIMARY KEY, " +
            RecordEntity.column_content+" TEXT, " +
            RecordEntity.column_type+" INTEGER, " +
            RecordEntity.column_record_date+" TEXT);";

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "qr_quick_scanner";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "qr_quick_scanner_record");
        onCreate(db);
    }
}
