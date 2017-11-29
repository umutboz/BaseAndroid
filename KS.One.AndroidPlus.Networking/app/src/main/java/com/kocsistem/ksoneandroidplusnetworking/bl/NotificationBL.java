package com.kocsistem.ksoneandroidplusnetworking.bl;

import android.content.Context;

import com.kocsistem.ksoneandroidplusnetworking.dal.notication.NotificationDal;
import com.kocsistem.ksoneandroidplusnetworking.model.basemodels.DavaresServiceErrorModel;
import com.kocsistem.ksoneandroidplusnetworking.model.basemodels.DavaresServiceModel;
import com.kocsistem.ksoneandroidplusnetworking.model.datamodels.NotificationInfo;
import com.kocsistem.ksoneandroidplusnetworking.sl.DavareSL;
import com.ks.network.service.listener.KSNetworkResponseListener;
import com.ks.network.service.model.ErrorModel;
import com.ks.network.service.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurkankesgin on 08/08/2017.
 */

public class NotificationBL extends BaseBL {

    private Context context;

    public NotificationBL(Context context) {
        super(context);
        this.context = context;
    }

    public void saveOffline(NotificationInfo NotificationInfo) {
        NotificationDal detailDAL = new NotificationDal(context);
        detailDAL.addNotificaion(NotificationInfo);
    }

    public ArrayList<NotificationInfo> getAllOffline() {
        NotificationDal detailDAL = new NotificationDal(context);
        ArrayList<NotificationInfo> allDetails = detailDAL.getAllNotifications();

        return allDetails;
    }

    public ArrayList<NotificationInfo> getByIdOffline(int id) {
        NotificationDal detailDAL = new NotificationDal(context);
        ArrayList<NotificationInfo> allDetails = (ArrayList<NotificationInfo>) detailDAL.getByNotificationsParameter(id);
        return allDetails;
    }

    public void getNotificationsOnline(final UIServiceCallback uiServiceCallback){
        DavareSL davareSL = new DavareSL(context);
        davareSL.getNotificationList(new KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel>() {
            @Override
            public void onSuccess(ResultModel<DavaresServiceModel> model) {

                List<NotificationInfo> notificationInfoList = (List<NotificationInfo>)model.getChildOjectList();
                uiServiceCallback.onSuccess(notificationInfoList);

            }

            @Override
            public void onError(ErrorModel<DavaresServiceErrorModel> errorModel) {

                uiServiceCallback.onError(errorModel);

            }

        });
    }

}
