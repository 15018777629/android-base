package com.yxr.baseandroid.base;

public class BaseConfig {
    public static boolean DEBUG = true;

    private BaseConfig() {
    }

    public static void init(boolean debug) {
        DEBUG = debug;
    }
}
