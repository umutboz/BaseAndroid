package com.kocsistem.ksoneandroidplusnetworking.dal.base;

import android.content.Context;
import android.util.Log;

import com.kocsistem.ksoneandroidplusnetworking.R;


public class AndroidLogger {

    public  static  void writeLog(Context context, String message)
    {
        Log.d(context.getString(R.string.app_name), message);
    }

}
