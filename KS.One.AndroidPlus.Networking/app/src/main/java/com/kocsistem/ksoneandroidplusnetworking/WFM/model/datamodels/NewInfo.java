package com.kocsistem.ksoneandroidplusnetworking.WFM.model.datamodels;

/**
 * Created by oguzhanalpayli on 24/11/2016.
 */

public class NewInfo  {

    int Id;
    String HeaderText;
    String ShortDescription;
    long Date;
    String ImagePath;
    int CreateDate;
    int IsUserFavourite;
    Integer UserLikeStatus;
    String Test;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHeaderText() {
        return HeaderText;
    }

    public void setHeaderText(String headerText) {
        HeaderText = headerText;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public long getDate() {
        return Date;
    }

    public void setDate(long date) {
        Date = date;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(int createDate) {
        CreateDate = createDate;
    }

    public int getIsUserFavourite() {
        return IsUserFavourite;
    }

    public void setIsUserFavourite(int isUserFavourite) {
        IsUserFavourite = isUserFavourite;
    }

    public Integer getUserLikeStatus() {
        return UserLikeStatus;
    }

    public void setUserLikeStatus(Integer userLikeStatus) {
        UserLikeStatus = userLikeStatus;
    }

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        Test = test;
    }
}
