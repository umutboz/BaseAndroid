package com.kocsistem.ksoneandroidplusnetworking.WFM.sl;

import android.content.Context;

import com.kocsistem.ksoneandroidplusnetworking.WFM.model.basemodels.DavaresServiceErrorModel;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.basemodels.DavaresServiceModel;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels.LoginRequestModel2;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels.NewInfo;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels.NotificationInfo;
import com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels.UserInfo;
import com.ks.network.service.core.KSNetworkConfig;
import com.ks.network.service.core.KSNetworkManager;
import com.ks.network.service.listener.KSNetworkResponseListener;


/**
 * Created by gurkankesgin on 19.6.2017 .
 */

public class DavareSL {

    private Context context;
    KSNetworkManager ksNetworkManager;
    public static final String RESULT_TAG = "ResultData";

    public DavareSL(Context mContex) {
        this.context = mContex;
        ksNetworkManager = new KSNetworkManager(context);
        ksNetworkManager.setNetworkException(new DavaresNetworkException(context));
        KSNetworkConfig.getInstance().setURL("http://37.131.250.218/Api/api/");
        KSNetworkConfig.getInstance().getDefaultHeaders().put("Cache-Control", "no-cache");
        KSNetworkConfig.getInstance().getDefaultHeaders().put("LanguageCode", "en");
        KSNetworkConfig.getInstance().getDefaultHeaders().put("Authorization", "ac9e6da4-b355-e711-80e1-001dd8b7221c");
        KSNetworkConfig.getInstance().getDefaultHeaders().put("ApiKey", "Zjg4MjEyN2EtYWM1OC00YzAxLTk5MDUtYTU2OTdhMjU0NmE0");
        KSNetworkConfig.getInstance().getDefaultHeaders().put("LocalDate", "1497869200");
        KSNetworkConfig.getInstance().getResultErrorCode().add(401);
    }

    public void getNotificationList(final KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel> listener) {

        String url = "User/NotificationList";
        KSNetworkConfig.getInstance().getDefaultHeaders().remove("UserToken");
        ksNetworkManager.getDataList(url, listener, NotificationInfo.class, DavaresServiceModel.class, RESULT_TAG);

    }

    public void getNew(final KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel> listener) {
        String url = "News/GetNews?NewsId=10";
        KSNetworkConfig.getInstance().getDefaultHeaders().put("UserToken", "96dfb14e-1890-417f-bd9c-bb1be76d3370");
        ksNetworkManager.getDataModel(url, listener, NewInfo.class, DavaresServiceModel.class, RESULT_TAG);

    }

    public void login(LoginRequestModel2 model, final KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel> listener) {
        String url = "Security/Login";
        KSNetworkConfig.getInstance().getDefaultHeaders().put("UserToken", "96dfb14e-1890-417f-bd9c-bb1be76d3370");
        ksNetworkManager.postDataModel(url, model, listener, UserInfo.class, DavaresServiceModel.class, RESULT_TAG);
    }

}
