package com.kocsistem.ksoneandroidplusnetworking.dal.base;

import android.content.ContentValues;

import java.util.List;


public interface DbQueryDAL {

    public ContentValues get(String param);
    public List<ContentValues> list(String[] columns, String tableName, String orderBy);
    public List<ContentValues> listByParam(String[] columns, String tableName, String orderBy, String param);

}
