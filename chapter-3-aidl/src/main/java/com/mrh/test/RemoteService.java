package com.mrh.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.mrh.test.aidl.Stub;

/**
 * Created by haoxinlei on 2019-12-26.
 */
public class RemoteService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Stub stub = new Stub() {
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };
        return stub;
    }
}
