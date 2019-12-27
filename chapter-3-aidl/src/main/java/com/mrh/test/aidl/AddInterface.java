package com.mrh.test.aidl;

import android.os.IInterface;

/**
 * 相当于我门定义的aidl文件
 * Created by haoxinlei on 2019-12-26.
 */
public interface AddInterface extends IInterface {
    String DESCRIBE = "com.mrh.aidl.AddInterface";

    int add(int a, int b);
}
