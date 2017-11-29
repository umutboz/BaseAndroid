package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base;

import android.content.ContentValues;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper<T> {


    public ContentValues getFieldDictionariesByType(T instance) {

        ContentValues dictionaries = new ContentValues();
        List<Field> fields = ReflectionHelper.getPrivateFields(instance.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(instance) != null) {
                    dictionaries.put(field.getName(), field.get(instance).toString());
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block

            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                System.out.println(field.getName());
            }

        }
        return dictionaries;
    }


    public static List<Field> getPrivateFields(Class<?> theClass) {
        List<Field> privateFields = new ArrayList<Field>();

        Field[] fields = theClass.getDeclaredFields();

        for (Field field : fields) {

            privateFields.add(field);

        }
        return privateFields;
    }

    public ContentValues getFieldDictionariesAnnotationByType(T instance) {

        ContentValues dictionaries = new ContentValues();
        List<Field> fields = ReflectionHelper.getPrivateFields(instance.getClass());
        for (Field field : fields) {
            field.setAccessible(true);

            Annotation annotation = field.getAnnotation(ColumnDAL.class);
            if (annotation != null) {
                ColumnDAL columnDAL = (ColumnDAL) annotation;
                String tagName = columnDAL.tag();
                try {
                    if (field.get(instance) != null) if (field.get(instance) != null) {
                        dictionaries.put(tagName, field.get(instance).toString());
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block

                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    System.out.println(field.getName());
                }
            }
        }

        return dictionaries;
    }


    /*
        boolean.class,boolean.
        byte.class
        char.class
        short.class
        int.class
        long.class
        float.class
        double.class
        void.class
     */



}
