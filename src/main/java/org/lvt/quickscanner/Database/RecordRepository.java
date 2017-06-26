package org.lvt.quickscanner.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.lvt.quickscanner.Others.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class RecordRepository {

    DataHelper dataHelper;
    SQLiteDatabase sqLiteDatabase;

    public RecordRepository(DataHelper dataHelper, SQLiteDatabase sqLiteDatabase) {
        this.dataHelper = dataHelper;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public List<RecordEntity> get(){

        List<RecordEntity> recordEntities = new ArrayList<>();
        sqLiteDatabase = dataHelper.getReadableDatabase();

        String[] columns = {
                RecordEntity._id,
                RecordEntity.column_content,
                RecordEntity.column_type,
                RecordEntity.column_record_date
        };

        Cursor cursor = sqLiteDatabase.query(RecordEntity.table,columns,null,null,null,null,RecordEntity.column_record_date + " DESC");
        while(cursor.moveToNext()) {
            RecordEntity entity = new RecordEntity();
            entity.setId(cursor.getInt(cursor.getColumnIndexOrThrow(RecordEntity._id)));
            entity.setContent(cursor.getString(cursor.getColumnIndexOrThrow(RecordEntity.column_content)));
            entity.setType(cursor.getInt(cursor.getColumnIndexOrThrow(RecordEntity._id)));
            Log.e("date: ", ""+cursor.getInt(cursor.getColumnIndexOrThrow(RecordEntity.column_record_date)));
            entity.setRecordDate(new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow(RecordEntity.column_record_date)))));
            recordEntities.add(entity);
        }
        return recordEntities;
    }

    public void insert(String content, Type type){
        sqLiteDatabase = dataHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RecordEntity.column_content,content);
        values.put(RecordEntity.column_type,type.toString());
        values.put(RecordEntity.column_record_date, String.valueOf(Calendar.getInstance().getTimeInMillis()));
        sqLiteDatabase.insert(RecordEntity.table,null,values);
    }

    public void delete(Integer[] ids){
        if(ids.length>0){
            sqLiteDatabase = dataHelper.getWritableDatabase();
            String[] args = Arrays.toString(ids).split("[\\[\\]]")[1].split(", ");
            for (String arg:args) {
                sqLiteDatabase.delete(RecordEntity.table, RecordEntity._id + " = "+arg, null);
            }
        }else{
            deleteAll();
        }
    }

    public void deleteAll(){
        sqLiteDatabase = dataHelper.getWritableDatabase();
        sqLiteDatabase.delete(RecordEntity.table,null,null);
    }
}
