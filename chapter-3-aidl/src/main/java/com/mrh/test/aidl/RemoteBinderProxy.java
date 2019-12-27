package com.mrh.test.aidl;

import android.os.IBinder;
import android.os.Parcel;

/**
 * 当 client 和 service 处于两个进程，用此类保存远程 Binder
 * Created by haoxinlei on 2019-12-26.
 */
public class RemoteBinderProxy implements AddInterface {

    /**
     * add 方法的编号
     */
    public static final int METHOD_ADD_CODE = 111;
    /**
     * 远程Binder对象，Client 和 Service处于两个进程
     */
    private IBinder mIBinder;

    public RemoteBinderProxy(IBinder IBinder) {
        mIBinder = IBinder;
    }

    @Override
    public int add(int a, int b) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int result = 0;
        try {
            data.writeInterfaceToken(Stub.DESCRIBE);
            data.writeInt(a);
            data.writeInt(b);
            //调用这个方法后，由于是跨进程的，当前进程会阻塞，等待reply的返回结果
            //所以 Binder 跨进程通信也是引起界面卡顿的一大原因
            mIBinder.transact(METHOD_ADD_CODE, data, reply, 0);
            reply.readException();
            result = reply.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return mIBinder;
    }
}
