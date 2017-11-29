package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.util.List;


public abstract class OfflineDbDAL<T> extends BaseDbDAL<T> implements DbWriterDAL,DbQueryDAL {


    public OfflineDbDAL(Context context) {
        super(context);
    }

    @Override
    public Long insert(ContentValues values, String TABLE_NAME) {
        try {
            return DbOperationsDAL.insert(context, getOpenDatabase(), values, TABLE_NAME);
        } catch (KSException e) {
            Log.d("DAL", e.getMessage());
            return Long.valueOf(-1);
        }

    }

    @Override
    public Long update(ContentValues values, String TABLE_NAME, String ColumnName, String Id) {
        try {
            return DbOperationsDAL.update(context, getOpenDatabase(), values, TABLE_NAME, ColumnName, Id);
        } catch (KSException e) {
            Log.d("DAL", e.getMessage());
            return Long.valueOf(-1);
        }
    }

    public Long deleteAll(String TABLE_NAME) {
        try {
            return DbOperationsDAL.deleteAll(context, getOpenDatabase(), TABLE_NAME);
        } catch (KSException e) {
            Log.d("DAL", e.getMessage());
            return Long.valueOf(-1);
        }
    }

    @Override
    public Long delete(String TABLE_NAME, String ColumnName, String Id) {
        try {
            return DbOperationsDAL.delete(context, getOpenDatabase(), TABLE_NAME, ColumnName, Id);
        } catch (KSException e) {
            Log.d("DAL", e.getMessage());
            return Long.valueOf(-1);
        }
    }

    @Override
    public ContentValues get(String param) {
        return null;
    }

    @Override
    public List<ContentValues> list(String[] columns, String tableName, String orderBy) {
        try {
             return DbOperationsDAL.list(context,getOpenDatabase(),tableName,columns,orderBy);
        } catch (KSException e) {
            AndroidLogger.writeLog(context,e.getMessage());
            return null;
        }
    }



    @Override
    public List<ContentValues> listByParam(String[] columns, String tableName, String orderBy, String param) {
        try {
            return DbOperationsDAL.listByParam(context,getOpenDatabase(),tableName,columns,param,orderBy);
        } catch (KSException e) {
            AndroidLogger.writeLog(context,e.getMessage());
            return null;
        }
    }

    public String getTableName(Class<T> clazz)
    {

        String tableName = clazz.getSimpleName();
        Annotation tableAnnotation =clazz.getAnnotation(TableDAL.class);
        if(tableAnnotation!=null)
        {
            TableDAL columnDAL = (TableDAL) tableAnnotation;
            tableName = columnDAL.tag();
        }
        return tableName;
    }




}
