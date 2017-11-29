package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ScriptGeneratorDAL<T> {

    //CREATE TABLE Category(code TEXT NULL,name TEXT NULL,priority INTEGER NULL)
    private final String CREATE_TABLE_SQL = "CREATE TABLE";
    private final String ALTER_TABLE_SQL = "ALTER TABLE";
    private final String ADD_COLUMN = "ADD COLUMN";
    private final String BLANK = " ";
    private final String PARENTHESIS_OPEN = "(";
    private final String PARENTHESIS_CLOSE = ")";
    private final String COMMA = ",";
    private final String STRING_TYPE = "TEXT";

    private final String INT_TYPE = "INTEGER";
    private final String BOOLEAN_TYPE = "BOOLEAN";
    private final String REAL_TYPE = "REAL_TYPE";

    public String getTableNameByClass(Class<T> clazz) {
        String tableName = clazz.getSimpleName();
        Annotation tableAnnotation = clazz.getAnnotation(TableDAL.class);
        if (tableAnnotation != null) {
            TableDAL columnDAL = (TableDAL) tableAnnotation;
            tableName = columnDAL.tag();
        }
        return tableName;
    }

    private String getColumnTypeByField(Field field) {
        String type = null;

        Class fieldClass = field.getType();
        if (fieldClass.getName().equals("java.lang.String")) {
            type = STRING_TYPE;
        } else if (fieldClass.getName().equals(int.class.getName())) {
            type = INT_TYPE;
        } else if (fieldClass.getName().equals(double.class.getName())) {
            type = REAL_TYPE;
        } else if (fieldClass.getName().equals(boolean.class.getName())) {
            type = BOOLEAN_TYPE;
        } else if (fieldClass.getName().equals(float.class.getName())) {
            type = REAL_TYPE;
        } else if (fieldClass.getName().equals(char.class.getName())) {
            type = STRING_TYPE;
        } else if (fieldClass.getName().equals(byte.class.getName())) {
            type = INT_TYPE;
        } else if (fieldClass.getName().equals(long.class.getName())) {
            type = INT_TYPE;
        }
        return type;
    }

    private List<Field> getPrivateFields(Class<?> theClass) {
        List<Field> privateFields = new ArrayList<Field>();

        Field[] fields = theClass.getDeclaredFields();

        for (Field field : fields) {
            privateFields.add(field);
        }
        return privateFields;
    }

    public String getTableScriptByDbModel(Class<T> clazz) {
        String script = "";
        List<Field> fields = getPrivateFields(clazz);
        String tableName = getTableNameByClass(clazz);

        script += CREATE_TABLE_SQL + BLANK + tableName + BLANK + PARENTHESIS_OPEN;
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(ColumnDAL.class);
            if (annotation != null) {
                ColumnDAL columnDAL = (ColumnDAL) annotation;

                String columnName = columnDAL.tag();
                String isNull = columnDAL.isNull() ? "NULL" : "NOT NULL";
                String type = getColumnTypeByField(field);
                script += BLANK + columnName + BLANK + type + BLANK + isNull + COMMA;
            }

        }
        int ind = script.lastIndexOf(COMMA);
        if (ind > 0) {
            script = script.substring(0, ind);
        }
        script += BLANK + PARENTHESIS_CLOSE;
        return script;
    }


    public void checkAlterByDbModel(Class<T> clazz, SQLiteDatabase db) {
        boolean isAlter = false;
        String script = "";
        List<Field> fields = getPrivateFields(clazz);
        String tableName = getTableNameByClass(clazz);
        Annotation tableAnnotation = clazz.getAnnotation(TableDAL.class);
        if (tableAnnotation != null) {
            TableDAL tableDAL = (TableDAL) tableAnnotation;
            String[] alterColumns = tableDAL.alter_columns() == "" ? null : (tableDAL.alter_columns().contains(",") ? tableDAL.alter_columns().split(",") : new String[]{tableDAL.alter_columns().toString()});
            boolean hasAlterColumns = false;
            if (alterColumns != null && alterColumns.length > 0) {
                for (String alterColumn : alterColumns) {
                    for (Field field : fields) {
                        Annotation annotation = field.getAnnotation(ColumnDAL.class);
                        if (annotation != null) {
                            ColumnDAL columnDAL = (ColumnDAL) annotation;
                            String columnName = columnDAL.tag();
                            if (alterColumn.equalsIgnoreCase(columnName)) {
                                hasAlterColumns = true;
                                script += ALTER_TABLE_SQL + BLANK + tableName + BLANK + ADD_COLUMN + BLANK;
                                String type = getColumnTypeByField(field);
                                script += columnName + BLANK + type + BLANK + "NULL" + COMMA;

                            }
                        }
                    }

                }


            }

            int ind = script.lastIndexOf(COMMA);
            if (ind > 0) {
                script = script.substring(0, ind);
            }
            if (hasAlterColumns && !hasColumnOnTable(db,tableName,alterColumns)) {
                db.execSQL(script);
            }
        }

    }

    protected boolean hasColumnOnTable(SQLiteDatabase db, String table, String[] alterColumns) {
        boolean hasColumnOnTable = false;
        try {
            Cursor dbCursor = db.query(table, null, null, null, null, null, null);
            String[] columns = dbCursor.getColumnNames();
            for (String alterCol : alterColumns) {
                for (String col : columns) {
                    if (alterCol.equalsIgnoreCase(col)) {
                        hasColumnOnTable = true;
                        break;
                    }
                }

            }
        } catch (SQLException ex) {
            Log.d("KS BASE", ex.getMessage());
            ex.printStackTrace();
        }
        return hasColumnOnTable;
    }

    private String removeLastComma(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}
