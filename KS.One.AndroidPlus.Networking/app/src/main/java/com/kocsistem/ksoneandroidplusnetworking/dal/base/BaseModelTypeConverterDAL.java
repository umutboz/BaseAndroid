package com.kocsistem.ksoneandroidplusnetworking.dal.base;

import android.content.ContentValues;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class BaseModelTypeConverterDAL<T> {


    T getInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception ex) {
            return null;
        }

    }


    public T getModelByAnnotationContentValues(ContentValues dictionaries, Class<T> clazz) {
        T instance = getInstance(clazz);
        List<Field> fields = ReflectionHelper.getPrivateFields(clazz);
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(ColumnDAL.class);
            if (annotation != null) {
                ColumnDAL columnDAL = (ColumnDAL) annotation;
                String tagName = columnDAL.tag();
                try {
                    Object dataString = getFieldAnnotationsValueByContentValues(dictionaries, field);

                    if (dataString != null) {
                        String fieldType = field.getType().getName();
                        try {
                            if  (fieldType.equals("java.lang.String")) {
                                field.set(instance, field.getType().cast(dataString));
                            }
                            else if (fieldType.equals(int.class.getName())) {
                                field.set(instance, Integer.parseInt(dataString.toString()));
                            } else if (fieldType.equals(double.class.getName())) {
                                field.set(instance, field.getType().cast(Double.parseDouble(dataString.toString())));
                            } else if (fieldType.equals(boolean.class.getName())) {
                                boolean booleanResult = false;
                                if(dataString.toString().equals("1"))
                                {
                                    booleanResult =true;
                                }
                                else if(dataString.toString().equalsIgnoreCase("true"))
                                {
                                    booleanResult =true;
                                }
                                field.set(instance, booleanResult);
                            } else if (fieldType.equals(float.class.getName())) {
                                field.set(instance, Float.parseFloat(dataString.toString()));
                            } else if (fieldType.equals(char.class.getName())) {
                                field.set(instance, dataString.toString());
                            } else if (fieldType.equals(byte.class.getName())) {
                                field.set(instance, Integer.parseInt(dataString.toString()));
                            } else if (fieldType.equals(long.class.getName())) {
                                field.set(instance, Long.parseLong(dataString.toString()));
                            }else if (fieldType.equals(Integer.class.getName())) {
                                field.set(instance, Integer.parseInt(dataString.toString()));
                            }

                        } catch (IllegalAccessException e) {

                        }
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block

                }
            }
        }
        return instance;
    }

    public Object getFieldAnnotationsValueByContentValues(ContentValues values, Field field) {
        Object data = null;
        if (values.size() > 0) {
            for (Map.Entry<String, Object> entry : values.valueSet()) {
                Annotation annotation = field.getAnnotation(ColumnDAL.class);
                if (annotation != null) {
                    ColumnDAL columnDAL = (ColumnDAL) annotation;
                    String tagName = columnDAL.tag();
                    if (entry.getKey().equalsIgnoreCase(tagName)) {
                        data = entry.getValue();
                        break;
                    }

                }

            }
        }
        return data;
    }

}
