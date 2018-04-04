package com.example.xiaozhu.helloworld;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by xiaozhu on 2017/12/1.
 */

public class UserLitepal extends DataSupport implements Serializable{
    private String name;
    private byte[] headshot;
    private String department;
    private String authority;
    private int id;
    private String telephone;
    private String mail;

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


    public String getDepartment() {
        return department;
    }

    public String getAuthority() {
        return authority;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }



    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



    //工号
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }




    //名字
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    //头像
    public byte[] getHeadshot() {
        return headshot;
    }
    public void setHeadshot(byte[] headshot) {
        this.headshot = headshot;
    }
    //bitmap转为二进制
    public static byte[] img(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}

/*
        //取出图片
        UserLitepal muser = DataSupport.findFirst(UserLitepal.class);
        Bitmap mheadshot = new Bitmap();
        mheadshot = muser.getHeadshot();

        //显示
        ImageView imageView = (ImageView)findViewById(R.id.image_cat);
        imageView.setImageBitmap(mbitmap);
*/