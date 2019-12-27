package com.mrh.test.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * 继承Binder,可以在跨进程之间传输
 * Created by haoxinlei on 2019-12-26.
 */
public abstract class Stub extends Binder implements AddInterface {

    public Stub() {
    }

    public static AddInterface asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        //当Client和Service处于同一进程的时候，此时的iBinder对象就是RemoteService中创建的Stub对象
        if (iBinder != null
                && iBinder instanceof AddInterface) {
            return (AddInterface) iBinder;
        }
        // 当client 和 Service 处于不同的进程，iBinder的类型是 BinderProxy（Binder的内部类）
        return new RemoteBinderProxy(iBinder);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        if (code == RemoteBinderProxy.METHOD_ADD_CODE) {
            data.enforceInterface(AddInterface.DESCRIBE);
            int a = data.readInt();
            int b = data.readInt();
            int result = add(a, b);
            reply.writeNoException();
            reply.writeInt(result);
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }
}
