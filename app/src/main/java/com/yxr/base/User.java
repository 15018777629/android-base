package com.yxr.base;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by 63062 on 2018/2/23.
 */

public class User {
    public final ObservableField<String> avatar = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
}
