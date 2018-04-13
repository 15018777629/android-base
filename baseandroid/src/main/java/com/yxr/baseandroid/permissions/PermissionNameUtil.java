package com.yxr.baseandroid.permissions;

import android.Manifest;
import android.text.TextUtils;

public class PermissionNameUtil {
    private PermissionNameUtil(){}

    public static String getPermissionName(String permission) {
        if (TextUtils.isEmpty(permission))
            return "";
        if (Manifest.permission.READ_CALENDAR.contains(permission))
            return "读取日历";
        if (Manifest.permission.WRITE_CALENDAR.contains(permission))
            return "写入日历";
        if (Manifest.permission.CAMERA.contains(permission))
            return "照相机";
        if (Manifest.permission.READ_CONTACTS.contains(permission))
            return "读取联系人";
        if (Manifest.permission.WRITE_CONTACTS.contains(permission))
            return "写入联系人";
        if (Manifest.permission.GET_ACCOUNTS.contains(permission))
            return "获取账户";
        if (Manifest.permission.ACCESS_FINE_LOCATION.contains(permission))
            return "访问精确位置";
        if (Manifest.permission.ACCESS_COARSE_LOCATION.contains(permission))
            return "访问粗略位置";
        if (Manifest.permission.RECORD_AUDIO.contains(permission))
            return "记录音频";
        if (Manifest.permission.READ_PHONE_STATE.contains(permission))
            return "读取手机状态";
        if (Manifest.permission.CALL_PHONE.contains(permission))
            return "拨打电话";
        if (Manifest.permission.READ_CALL_LOG.contains(permission))
            return "读取通话记录";
        if (Manifest.permission.WRITE_CALL_LOG.contains(permission))
            return "写入通话记录";
        if (Manifest.permission.ADD_VOICEMAIL.contains(permission))
            return "添加语音信箱";
        if (Manifest.permission.USE_SIP.contains(permission))
            return "使用SIP";
        if (Manifest.permission.PROCESS_OUTGOING_CALLS.contains(permission))
            return "线程输出调用";
        if (Manifest.permission.BODY_SENSORS.contains(permission))
            return "传感器";
        if (Manifest.permission.SEND_SMS.contains(permission))
            return "发送短信";
        if (Manifest.permission.RECEIVE_SMS.contains(permission))
            return "接收短信";
        if (Manifest.permission.READ_SMS.contains(permission))
            return "查看短信";
        if (Manifest.permission.RECEIVE_WAP_PUSH.contains(permission))
            return "接收WAP推送";
        if (Manifest.permission.RECEIVE_MMS.contains(permission))
            return "接收彩信";
        if (Manifest.permission.READ_EXTERNAL_STORAGE.contains(permission))
            return "读取外部存储器";
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.contains(permission))
            return "写入外部存储";
        return "";
    }
}
