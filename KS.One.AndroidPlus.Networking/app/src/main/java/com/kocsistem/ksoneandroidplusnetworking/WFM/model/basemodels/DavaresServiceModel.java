package com.kocsistem.ksoneandroidplusnetworking.WFM.model.basemodels;

import com.google.gson.JsonObject;

/**
 * Created by gurkankesgin on 19.6.2017 .
 */

public class DavaresServiceModel<T> {
    private String ResultMessage;
    private T ResultData;
    private int ResultCode;
    private JsonObject jsonObject;


    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.ResultMessage = resultMessage;
    }

    public T getResultData() {
        return ResultData;
    }

    public void setResultData(T resultData) {
        this.ResultData = resultData;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

}
