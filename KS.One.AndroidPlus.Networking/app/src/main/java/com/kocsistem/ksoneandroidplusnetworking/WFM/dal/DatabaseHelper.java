package com.kocsistem.ksoneandroidplusnetworking.WFM.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base.ScriptGeneratorDAL;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels.NotificationInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase dbInstance;

    private final Context context;
    private static List<Object> dbModels = new ArrayList<Object>();
    private static List<String> dbScripts = new ArrayList<String>();

    public DatabaseHelper(Context context) {
        super(context, ConstantsDB.DATABASE_NAME, null, ConstantsDB.DATABASE_VERSION);
        this.context = context;
    }

    public DatabaseHelper(Context context, String dbPath) {
        super(context, dbPath, null, ConstantsDB.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Örnek Script

        db.execSQL(new ScriptGeneratorDAL<NotificationInfo>().getTableScriptByDbModel(NotificationInfo.class));
    }

    public static String readRawTextFile(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            return null;
        }
        return byteArrayOutputStream.toString();
    }

    public void addModel(Object dbModel) {
        dbModels.add(dbModel);
        dbScripts.clear();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //TODO: Güncellenme örneği
        if (db.getVersion() < newVersion) {
            try {
                new ScriptGeneratorDAL<NotificationInfo>().checkAlterByDbModel(NotificationInfo.class, db);
                db.setVersion(newVersion);
            } catch (Exception ex) {
                ex.getMessage();
            }
        }

    }

    public static boolean checkDb(Context context) {
        File dbFile = new File(context.getDatabasePath(ConstantsDB.DATABASE_NAME).toString());
        if (dbFile.exists()) {
            Log.d("MobileMarketingDb", "Exists File");
            return true;
        } else {
            Log.d("CATALOG", "No Exists File");
            return false;
        }
    }

    public static int getDbVersion(Context context) {
        if (checkDb(context)) {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(
                    context.getDatabasePath(ConstantsDB.DATABASE_NAME).toString(), null,
                    SQLiteDatabase.OPEN_READONLY);
            int Version = db.getVersion();
            db.close();
            return Version;
        } else {
            return -1;
        }

    }

    public static SQLiteDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (dbInstance == null) {
                    DatabaseHelper helper = new DatabaseHelper(context);
                    dbInstance = helper.getWritableDatabase();
                }
            }
        }

        return dbInstance;
    }

    public static SQLiteDatabase getInstance(Context context, String dbPath) {
        if (dbInstance == null) {
            synchronized (DatabaseHelper.class) {
                if (dbInstance == null) {
                    DatabaseHelper helper = new DatabaseHelper(context);
                    dbInstance = helper.getWritableDatabase();
                }
            }
        }

        return dbInstance;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void dropDatabase(Context context, String dbPath) {
        try {
            SQLiteDatabase.deleteDatabase(new File(dbPath));
        } catch (Exception ex) {
            Log.d("DROP Db", ex.getMessage());
        }


    }

    public static void dropTable(Context context, String tableName) {
        SQLiteDatabase db = getInstance(context);
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }

    }

}
