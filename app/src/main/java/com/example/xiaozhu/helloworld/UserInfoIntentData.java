package com.example.xiaozhu.helloworld;

import java.io.Serializable;
//序列化userLitepal对象使得可以通过intent来传输(注意UserLitepal里的成员也要实现Serializable接口)
public class UserInfoIntentData implements Serializable {
    private UserLitepal userLitepal;

    public UserLitepal getUserLitepal() {
        return userLitepal;
    }

    public void setUserLitepal(UserLitepal userLitepal) {
        this.userLitepal = userLitepal;
    }
}
