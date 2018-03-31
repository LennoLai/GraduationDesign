package com.example.xiaozhu.helloworld;

import android.graphics.Bitmap;

/**
 * Created by xiaozhu on 2017/11/7.
 */

public class UserInfo {
    private String name;
    private Bitmap imageId_user;


    public UserInfo(String name, Bitmap imageId_user){
        this.name = name;
        this.imageId_user = imageId_user;

    }

    public String getName(){
        return name;
    }

    public Bitmap getImageId_user(){
        return imageId_user;
    }


}
