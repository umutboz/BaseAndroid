package com.kocsistem.ksoneandroidplusnetworking.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kocsistem.ksoneandroidplusnetworking.R;
import com.kocsistem.ksoneandroidplusnetworking.bl.NotificationBL;
import com.kocsistem.ksoneandroidplusnetworking.bl.UIServiceCallback;
import com.kocsistem.ksoneandroidplusnetworking.model.basemodels.DavaresServiceErrorModel;
import com.kocsistem.ksoneandroidplusnetworking.model.basemodels.DavaresServiceModel;
import com.kocsistem.ksoneandroidplusnetworking.model.datamodels.LoginRequestModel2;
import com.kocsistem.ksoneandroidplusnetworking.model.datamodels.NewInfo;
import com.kocsistem.ksoneandroidplusnetworking.model.datamodels.NotificationInfo;
import com.kocsistem.ksoneandroidplusnetworking.model.datamodels.UserInfo;
import com.kocsistem.ksoneandroidplusnetworking.sl.DavareSL;
import com.ks.network.service.listener.KSNetworkResponseListener;
import com.ks.network.service.model.ErrorModel;
import com.ks.network.service.model.ResultModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    NotificationBL notificationBL = new NotificationBL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = new Button(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // region post ve getModel örnekleri
        DavareSL davareSL = new DavareSL(this);

        davareSL.getNew(new KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel>() {
            @Override
            public void onSuccess(ResultModel<DavaresServiceModel> model) {

                NewInfo newInfo = (NewInfo) model.getChildObject();


            }

            @Override
            public void onError(ErrorModel<DavaresServiceErrorModel> errorModel) {
                if (errorModel.getErrorModel() != null) {
                    // response hataları yanlış kullanıcı adı , hatalı parametre gibi
//                    Log.d("", errorModel.getErrorModel().getMessage());
                } else {
                    // daha üst seviye hatalar -- response gelmeyen -- internet erişim server error gibi
//                    Log.d("", errorModel.getDescription());
                }
            }

        });


        davareSL.login(new LoginRequestModel2("admin@admin.com", "1!qaz@"), new KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel>() {
            @Override
            public void onSuccess(ResultModel<DavaresServiceModel> model) {

                UserInfo newInfo = (UserInfo) model.getChildObject();


            }

            @Override
            public void onError(ErrorModel<DavaresServiceErrorModel> errorModel) {

            }

        });


        // endregion


        notificationBL.getNotificationsOnline(new UIServiceCallback() {
            @Override
            public void onSuccess(Object object) {
                List<NotificationInfo> notificationInfoList = (List<NotificationInfo>) object;


                for (NotificationInfo notificationInfo : notificationInfoList) {

                }
            }

            @Override
            public void onError(Object object) {
                object.toString();
            }
        });


        // Offline

        // region var olan database i sil

      /*  File file = new File("data/data/com.kocsistem.ksoneandroidplusnetworking/databases/"+ ConstantsDB.DATABASE_NAME);
        if (file.exists()) {
            file.delete();
        }*/

        // endregion

        // region dummy

        NotificationInfo notificationInfo = new NotificationInfo();
        notificationInfo.setContentTypeId(1);
        notificationInfo.setText("text");

        // endregion

        // region save


        notificationBL.saveOffline(notificationInfo);


        // endregion

        // region getall

        for (NotificationInfo info : notificationBL.getAllOffline()) {
            Log.d("notification", info.toString());
        }

        // endregion

        // region getbyId

        List<NotificationInfo> notificationById = notificationBL.getByIdOffline(1);
        Log.d("notification", notificationById.toString());

        // endregion
    }


}
