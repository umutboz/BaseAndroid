package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base;

import android.content.ContentValues;

public interface DbWriterDAL {
    public  Long insert(ContentValues values, String TABLE_NAME);
    public  Long update(ContentValues values, String TABLE_NAME, String ColumnName, String Id);
    public Long delete(String TABLE_NAME, String ColumnName, String id);
}
