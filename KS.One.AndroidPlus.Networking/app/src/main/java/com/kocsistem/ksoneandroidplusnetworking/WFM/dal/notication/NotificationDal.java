package com.kocsistem.ksoneandroidplusnetworking.WFM.dal.notication;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base.OfflineDbDAL;
import com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base.ReflectionHelper;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels.NotificationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurkankesgin on 24/07/2017.
 */

public class NotificationDal extends OfflineDbDAL<NotificationInfo> {

    public NotificationDal(Context context) {
        super(context);
    }

    public long addNotificaion(NotificationInfo model) {
        long returnValue = 0;
        try {

            ContentValues contentValues = new ReflectionHelper<NotificationInfo>().getFieldDictionariesAnnotationByType(model);
            returnValue = this.insert(contentValues, getTableName(NotificationInfo.class));

        } catch (Exception ex) {
            Log.d("NewDAL", ex.getMessage());
            returnValue = -1;
        }
        return returnValue;

    }

    public long addNotifications(List<NotificationInfo> models) {
        long returnValue = 0;
        try {
            for (NotificationInfo m : models) {
                returnValue = addNotificaion(m);
            }
        } catch (Exception ex) {
            Log.d("NewDAL", ex.getMessage());
            returnValue = -1;
        }
        return returnValue;

    }

    public long deleteAllNotifications() {
        return this.deleteAll(getTableName(NotificationInfo.class));
    }

    public ArrayList<NotificationInfo> getAllNotifications() {

        List<ContentValues> values = list(getQueryColumns(NotificationInfo.class), getTableName(NotificationInfo.class), null);
        return listModelByContentValues(values, NotificationInfo.class);
    }

    public ArrayList<NotificationInfo> getByNotificationsParameter(int id) {

        List<ContentValues> values = listByParam(getQueryColumns(NotificationInfo.class), getTableName(NotificationInfo.class),"ID Desc","TEXT = " + "text");
        return listModelByContentValues(values, NotificationInfo.class);
    }

    @Override
    public NotificationInfo getModel(ContentValues values) {
        return getModelAnnotationsByContentValues(values, NotificationInfo.class);
    }

    public List<NotificationInfo> getNotificationsById(int id){
        List<ContentValues> values = listByParam(getQueryColumns(NotificationInfo.class), getTableName(NotificationInfo.class), null, "ID=" + "" + id);
        return listModelByContentValues(values, NotificationInfo.class);
    }

    public long deleteNotifications(int id){
        return this.delete(getTableName(NotificationInfo.class),"ID",String.valueOf(id));

    }

}
