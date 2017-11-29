package com.kocsistem.ksoneandroidplusnetworking.dal.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kocsistem.ksoneandroidplusnetworking.R;

import java.util.ArrayList;
import java.util.List;


public class  DbOperationsDAL {

    public static Long insert(Context context, SQLiteDatabase db, ContentValues values, String TABLE_NAME) throws KSException {
        long result = 0;
        try {
            result = db.insert(TABLE_NAME, null, values);
            return result;
        } catch (Exception ex) {
            throw new KSException(context.getString(R.string.db_insert_error), ex, ErrorType.Insert);
        }
    }

    public static Long deleteAll(Context context, SQLiteDatabase db, String TABLE_NAME) throws KSException {

        long result = 0;
        try {
            result = db.delete(TABLE_NAME,"",null);
            return result;
        } catch (Exception ex) {
            throw new KSException(context.getString(R.string.db_insert_error), ex, ErrorType.Insert);
        }
    }

    public static Long delete(Context context, SQLiteDatabase db, String TABLE_NAME, String ColumnName, String id) throws KSException {
        long result = 0;
        try {
            result = db.delete(TABLE_NAME, ColumnName + "=?", new String[]{id});
            return result;
        } catch (Exception ex) {
            throw new KSException(context.getString(R.string.db_insert_error), ex, ErrorType.Insert);
        }
    }

    public static Long update(Context context, SQLiteDatabase db, ContentValues values, String TABLE_NAME, String ColumnName, String Id) throws KSException {
        long result = 0;

        try {
            Long isDeleteResult = delete(context, db, TABLE_NAME, ColumnName + "=?", Id);
            if (isDeleteResult != 0) {
                result = insert(context, db, values, TABLE_NAME);
            }

            return result;
        } catch (Exception e) {
            throw new KSException(context.getString(R.string.db_update_error), ErrorType.Update);
        }

    }


    public static List<ContentValues> list(Context context, SQLiteDatabase db, String tableName, String[] columns, String orderBy) throws KSException {
        List<ContentValues> contentValueList = new ArrayList<ContentValues>();
        Cursor cursor = null;

        try {
            cursor = listCursor(db, tableName, columns, null, orderBy);
            if (cursor != null && cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {
                    ContentValues contentValues = cursorToContentValues(context, cursor, columns);
                    contentValueList.add(contentValues);

                    cursor.moveToNext();
                }
            }

            return contentValueList;
        } catch (Exception ex) {
            Log.d("Db Hata", ex.getMessage());
            //throw new KSException(context.getString(R.string.db_list_error), ex, ErrorType.List);
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return contentValueList;
    }

    public static List<ContentValues> listByParam(Context context, SQLiteDatabase db, String tableName, String[] columns, String params, String orderBy) throws KSException {
        List<ContentValues> contentValueList = new ArrayList<ContentValues>();
        Cursor cursor = null;

        try {
            cursor = listCursor(db, tableName, columns, params, orderBy);

            if (cursor != null && cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false) {
                    ContentValues contentValues = cursorToContentValues(context, cursor, columns);
                    contentValueList.add(contentValues);

                    cursor.moveToNext();
                }
            }

            return contentValueList;
        } catch (Exception ex) {
            AndroidLogger.writeLog(context, ex.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return contentValueList;
    }

    public static Cursor listCursor(SQLiteDatabase db, String TABLE_NAME, String[] QUERY_COLUMNS, String params, String DEFAULT_SORT_ORDER) {
        String rawQuery = "SELECT  ";
        for (int i = 0; i < QUERY_COLUMNS.length; i++) {
            if (QUERY_COLUMNS.length - 1 != i) {
                rawQuery += QUERY_COLUMNS[i] + ",";
            } else {
                rawQuery += QUERY_COLUMNS[i];
            }
        }
        rawQuery += " FROM " + TABLE_NAME;
        if (params != null) {
            rawQuery += " WHERE " + params;
        }
        if (DEFAULT_SORT_ORDER != null) {
            rawQuery += " order by " + DEFAULT_SORT_ORDER;
        }

        return db.rawQuery(rawQuery, null);

    }

    public static ContentValues cursorToContentValues(Context context, Cursor cursor, String[] columns) throws KSException {
        ContentValues info = new ContentValues();
        try {
            for (String key : columns) {
                String data = null;
                try {
                    data = cursor.getString(cursor.getColumnIndex(key));
                    info.put(key, data);
                } catch (Exception ex) {
                    AndroidLogger.writeLog(context, "cursorToContentValues" + ex.getMessage());
                }

            }

            return info;
        } catch (Exception ex) {
            AndroidLogger.writeLog(context, "cursorToContentValues" + ex.getMessage());
            //throw new KSException(context.getString(R.string.db_cursorToInfo_error), ex, ErrorType.CursorToInfo);
        }
        return info;
    }

}
