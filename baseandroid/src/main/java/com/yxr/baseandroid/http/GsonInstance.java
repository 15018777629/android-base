package com.yxr.baseandroid.http;

import com.google.gson.Gson;

/**
 * Created by 63062 on 2017/11/29.
 */

public class GsonInstance {
    private GsonInstance(){}

    public static Gson instance(){
        return InstanceHolder.instance;
    }

    static class InstanceHolder{
        static final Gson instance = new Gson();
    }
}
