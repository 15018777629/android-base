# android-base
MVVM模式的安卓基本依赖，包含了网络访问封装，权限申请封装和其他一些基本。
## 如何使用
```java
compile 'com.yxr.android:base-android:0.0.3'
// 在相应的Module中加入
dataBinding {
        enabled = true
    }
// 还需要依赖一些常用的第三方的类库
compile 'com.lzy.net:okgo:3.0.4'
compile 'com.lzy.net:okrx2:2.0.2'
compile 'com.google.code.gson:gson:2.8.2'
compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
compile 'io.reactivex.rxjava2:rxjava:2.1.3'
compile 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.5@aar'
compile 'com.github.bumptech.glide:glide:4.0.0-RC1'
```
