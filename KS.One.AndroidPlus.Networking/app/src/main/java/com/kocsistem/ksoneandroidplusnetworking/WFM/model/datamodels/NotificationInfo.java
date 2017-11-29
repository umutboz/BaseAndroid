package com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels;

import com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base.ColumnDAL;
import com.kocsistem.ksoneandroidplusnetworking.WFM.dal.base.TableDAL;

/**
 * Created by gurkankesgin on 19.6.2017 .
 */

@TableDAL(tag = "NOTIFICATION_INFO")
public class NotificationInfo {

    @ColumnDAL(tag = "ID")
    private int ContentTypeId;

    @ColumnDAL(tag = "TEXT")
    private String Text;

    public Integer getContentTypeId() {
        return ContentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        ContentTypeId = contentTypeId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
