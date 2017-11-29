package com.kocsistem.ksoneandroidplusnetworking.sl;

import android.content.Context;

import com.google.gson.Gson;
import com.kocsistem.ksoneandroidplusnetworking.model.basemodels.DavaresServiceErrorModel;
import com.kocsistem.ksoneandroidplusnetworking.model.basemodels.DavaresServiceModel;
import com.ks.network.service.core.KSNetworkConfig;
import com.ks.network.service.exception.KSNetworkException;
import com.ks.network.service.listener.KSNetworkResponseListener;
import com.ks.network.service.model.ErrorModel;
import com.ks.network.service.model.ResultModel;


/**
 * Created by gurkankesgin on 16.6.2017 .
 */

public class DavaresNetworkException extends KSNetworkException<DavaresServiceModel, DavaresServiceErrorModel> {

    private Context context;

    public DavaresNetworkException(Context context) {
        this.context=context;
    }

    @Override
    public void checkSuccessData(KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel> listener, ResultModel<DavaresServiceModel> resultModel) {


        //servisin kendi hata senaryosunu öğret

        DavaresServiceModel davaresServiceModel = new Gson().fromJson(resultModel.getJson(), DavaresServiceModel.class);

        if (davaresServiceModel.getResultCode() != 0) {

            ErrorModel<DavaresServiceErrorModel> errorModelG = new ErrorModel<>();
            DavaresServiceErrorModel errorModel = new DavaresServiceErrorModel();
            errorModel.setCode(davaresServiceModel.getResultCode());
            errorModel.setMessage(davaresServiceModel.getResultMessage());

            errorModelG.setErrorModel(errorModel);
            listener.onError(errorModelG);

        } else {
            listener.onSuccess(resultModel);
        }


    }

    @Override
    public void checkSuccessDataList(KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel> listener, ResultModel<DavaresServiceModel> resultModel) {

        DavaresServiceModel davaresServiceModel = new Gson().fromJson(resultModel.getJson(), DavaresServiceModel.class);

        if (davaresServiceModel.getResultCode() != 0) {

            ErrorModel<DavaresServiceErrorModel> errorModelG = new ErrorModel<>();
            DavaresServiceErrorModel errorModel = new DavaresServiceErrorModel();
            errorModel.setCode(davaresServiceModel.getResultCode());
            errorModel.setMessage(davaresServiceModel.getResultMessage());

            errorModelG.setErrorModel(errorModel);
            listener.onError(errorModelG);

        } else {

            listener.onSuccess(resultModel);
        }

    }

    // 200 gelmeyen durum -- verdiğin özel http hata kodlarına göre dönen model
    @Override
    public void checkErrorData(KSNetworkResponseListener<DavaresServiceModel, DavaresServiceErrorModel> listener, ErrorModel<DavaresServiceErrorModel> errorModel) {

        for (Integer serviceErrorCode : KSNetworkConfig.getInstance().getResultErrorCode()) {
            if (Integer.parseInt(errorModel.getErrorCode()) == serviceErrorCode) {

            }
        }

        listener.onError(errorModel);

    }
}

