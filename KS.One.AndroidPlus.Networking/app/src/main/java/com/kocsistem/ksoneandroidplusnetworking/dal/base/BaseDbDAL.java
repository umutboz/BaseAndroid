package com.kocsistem.ksoneandroidplusnetworking.dal.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kocsistem.ksoneandroidplusnetworking.dal.DatabaseHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDbDAL<T> {




    protected Context context;
    protected SQLiteDatabase dbInstance;
    protected String DbPath;
    public BaseDbDAL(Context context)
    {
        this.context = context;
        if(DbPath!=null)
        {
            dbInstance = DatabaseHelper.getInstance(context,DbPath);
        }else
        {
            dbInstance = DatabaseHelper.getInstance(context);
        }


    }

    public  SQLiteDatabase getOpenDatabase()
    {
        if(dbInstance!=null)
        {
            return dbInstance;
        }
        return null;
    }



    public abstract T getModel(ContentValues values);

    /*
    public  T getModelByContentValues(ContentValues values,T instance)
    {
        BaseModelTypeConverterDAL<T> typeConverterDAL = new BaseModelTypeConverterDAL<T>();

        return typeConverterDAL.getModelByContentValues(values,instance);
    }
    */


    public ArrayList<T> listModelByContentValues(List<ContentValues> values, Class<T> clazz)
    {

        ArrayList<T> list = new ArrayList<T>();

        for (ContentValues entry : values) {
            T model = getModel(entry);
            list.add(model);
        }

        return list;
    }


    public  T getModelAnnotationsByContentValues(ContentValues values,Class<T> clazz)
    {
        BaseModelTypeConverterDAL<T> typeConverterDAL = new BaseModelTypeConverterDAL<T>();

        return typeConverterDAL.getModelByAnnotationContentValues(values,clazz);
    }
    private List<Field> getPrivateFields(Class<?> theClass) {
        List<Field> privateFields = new ArrayList<Field>();

        Field[] fields = theClass.getDeclaredFields();

        for (Field field : fields) {
            privateFields.add(field);
        }
        return privateFields;
    }
    public String[] getQueryColumns(Class<T> tClass)
    {
        List<String> columnList = new ArrayList<String>();

        List<Field> fields =getPrivateFields(tClass);

        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(ColumnDAL.class);
            if (annotation != null) {
                ColumnDAL columnDAL = (ColumnDAL) annotation;
                columnList.add(columnDAL.tag());
            }

        }
        int size = columnList.size();
        String[] columns = new String[size];
        if(size>0)
        {
            for (int i = 0; i< size;i++)
            {
                columns[i]= columnList.get(i);
            }
        }

        return columns;
    }

}
