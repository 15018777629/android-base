package com.yxr.baseandroid.permissions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yxr.baseandroid.dialog.DesignAlertDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by 63062 on 2017/8/28.
 */

public class PermissionUtil {
    private static List<String> refusePermissions = new ArrayList<>();
    private static List<String> refuseNoAskingPermissions = new ArrayList<>();
    private static int index = 0;

    private PermissionUtil(){}

    /**
     * 动态申请单个权限
     *
     * @param permission ： 申请的权限
     * @param listener   ： 权限申请成功回调
     */
    public static void requestPermission(Activity activity, String permission, GrantedListener listener) {
        requestPermission(activity, permission, null, listener);
    }

    /**
     * 动态申请单个权限
     *
     * @param permission      ： 申请的权限
     * @param refuseListener  ： 权限拒绝，拒绝且永不询问回调
     * @param grantedListener ： 权限申请成功回调
     */
    public static void requestPermission(final Activity activity, final String permission
            , final RefuseListener refuseListener, final GrantedListener grantedListener) {
        new RxPermissions(activity).request(permission)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            if (grantedListener != null)
                                grantedListener.granted();
                        } else {
                            if (refuseListener != null) {
                                refuseListener.refuse();
                                return;
                            }
                            showSettingDialog(activity, permission);
                        }
                    }
                });
    }

    /**
     * 动态申请多个权限
     *
     * @param listener    ： 权限申请回调，返回拒绝权限和拒绝永不询问权限
     * @param permissions ： 申请的权限s
     */
    public static void requestPermissions(Activity activity, final PermissionsListener listener, final String... permissions) {
        init();
        new RxPermissions(activity).requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // `permission.name` is granted !
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            refusePermissions.add(permission.name);
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            refuseNoAskingPermissions.add(permission.name);
                        }
                        index++;
                        if (index >= permissions.length) {
                            if (listener == null)
                                return;
                            listener.finish(refusePermissions, refuseNoAskingPermissions);
                        }
                    }
                });
    }

    public static void showSettingDialog(final Activity activity, String permission) {
        final DesignAlertDialog dialog = new DesignAlertDialog(activity);
        dialog.setTitle("需要手动申请【" + PermissionNameUtil.getPermissionName(permission) + "】权限");
        dialog.setDefiniteClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                toSetting(activity);
            }
        });
        dialog.show();
    }

    /**
     * 跳转到权限设置界面
     */
    private static void toSetting(Activity activity) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        activity.startActivity(intent);
    }

    private static void init() {
        index = 0;
        refusePermissions.clear();
        refuseNoAskingPermissions.clear();
    }

    public interface PermissionsListener {
        void finish(List<String> refusePermissions, List<String> refuseNoAskingPermissions);
    }

    public interface GrantedListener {
        void granted();
    }

    public interface RefuseListener {
        void refuse();
    }
}
