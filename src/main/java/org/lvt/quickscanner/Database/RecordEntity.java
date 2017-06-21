package org.lvt.quickscanner.Database;

import java.util.Date;

/**
 * Created by ylt1hc on 6/19/2017.
 */
public class RecordEntity {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    int    id;
    String content;
    int   type;
    Date   recordDate;

    public static String table ="qr_quick_scanner_record";
    public static String _id = "id";
    public static String column_content = "content";
    public static String column_type = "type";
    public static String column_record_date = "date";

}
