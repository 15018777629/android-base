# android-base
MVVM模式的安卓基本依赖，包含了网络访问封装，权限申请封装和其他一些基本。
## 如何使用

* 添加依赖

```java
// 如果module使用MVVM模式在相应的Module中
android{
	...
	dataBinding {
    	enabled = true
	}    
}

compile 'com.yxr.android:base-android:0.0.24'
```

* MVC模式下如何使用BaseActivity和BaseFragment

```
// Fragment类似，具体参照demo
public class MVCActivity extends BaseActivity {
	...
	@Override
    public BasePresenter initBasePresenter() {
        // MVC只需要return null即可
        return null;
    }
}
```

* MVP模式下如何使用BaseActivity和BaseFragment

```
// 指定Activity泛型BaseActivity<BasePresenter>(fragment 同理)，实现继承IBaseView的View接口
public class MVPActivity extends BaseActivity<BasePresenter> implements IMVPActivityView {
	...
	@Override
    public BasePresenter initBasePresenter() {
        // MVP 在这里可以直接return BasePresenter的子类，详细参考demo
        // 参数依次为 ：上下文 - IBaseView 
        return new MVPActivityPresenter(this, this);
    }
}
```

* MVVM模式下如何使用BaseActivity和BaseFragment

  ```
  // 指定Activity泛型BaseActivity<BaseViewModel>(fragment 同理）
  public class MVVMActivity extends BaseActivity<BaseViewModel> {
  	...
  	@Override
      public BaseViewModel initBasePresenter() {
      	// return BaseViewModel的子类，详细参考demo
          return new MVVMActivityViewModel(this);
      }
  }

  ```

* 带有多状态（loading，empty，netError）的Activity和Fragment使用：和BaseActivity与BaseFragment使用方式一致，只是继承的基类更换

  ```
  public class MvcStatusActivity extends BaseStatusActivity {
  	...
  	// 详细参考demo
  	private void initBizData() {
  	    // 切换到loading状态
          showLoading();
          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                  tvContent.setText("init complete!!");
                  // 结束loading状态，切换到内容状态
                  dismissLoading();
                  // 结束loading状态，HttpCode.EXCEPTION_NO_CONNECT == httpCode || 		HttpCode.EXCEPTION_TIME_OUT == httpCode 时切换到网络异常状态 否则 showEmpty == true 时切换到空布局状态，否则切换到内容布局状态
                  // dismissLoading(showEmpty, httpCode);
              }
          },2500);
      }
  }

  ```

* 网络请求使用

  ```
  // 参数依次为 ：url，map，回调
  HttpUtil.obGet("http://op.juhe.cn/onebox/football/league?key=bbdf40a269d0f08936ddb07b076be559&league=%E6%B3%95%E7%94%B2"
      , null, new HttpCallBack<String>(context) {
          @Override
          public void onSuccess(String s) {
              mvpView.loadStatus(1);
          }

          @Override
          public void onError(int code, String msg) {
              mvpView.loadStatus(1);
          }
      });
  // 建议在onDestroy中调用（所有继承BaseActivity和BaseFragment的Activity与Fragment无需进行此操作）
  public void onDestroy() {
      HttpUtil.clearDisposable(context);
  }
  ```

* 权限申请

  ```
  // 申请单个权限
  PermissionUtil.requestPermission(activity, Manifest.permission.CAMERA, new PermissionUtil.GrantedListener() {
      @Override
      public void granted() {
  		...
  		// get permission to do something
  	}
  });

  // 申请多个权限
  String[] permissions = {Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
  PermissionUtil.requestPermissions(this, new PermissionUtil.PermissionsListener() {
      @Override
      public void finish(List<String> refusePermissions,List<String> refuseNoAskingPermissions){
  		...
          // refusePermissions 拒绝的权限，
          // refuseNoAskingPermissions 没有弹框询问就被拒绝的权限
      }
  }, permissions);
      
  ```