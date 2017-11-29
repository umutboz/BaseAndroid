package com.kocsistem.ksoneandroidplusnetworking.dal.base;

import android.util.SparseArray;


public enum ErrorType {
    Insert(10000),
    Delete(10001),
    Update(10002),
    List(10003),
    CursorToInfo(10004)
 ;

    public final int Value;

    private ErrorType(int value)
    {
        Value = value;
    }

    private static final SparseArray<ErrorType> _map = new SparseArray<ErrorType>();
    static
    {
        for (ErrorType data : ErrorType.values())
            _map.put(data.Value, data);
    }

    public static ErrorType from(int value)
    {
        return _map.get(value);
    }

}
