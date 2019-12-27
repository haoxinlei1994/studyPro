package com.mrh.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mrh.test.aidl.AddInterface;
import com.mrh.test.aidl.Stub;

public class AidlActivity extends AppCompatActivity {

    private Button mBindServiceBtn;
    private Button mAddBtn;
    private AddInterface mAddInterface;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mBindServiceBtn = findViewById(R.id.btn_bind_service);
        mAddBtn = findViewById(R.id.btn_add);
        mBindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performBindService();
            }
        });
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAdd();
            }
        });
    }

    private void performBindService() {
        Intent intent = new Intent(this, RemoteService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mAddInterface = Stub.asInterface(service);
                Toast toast = Toast.makeText(AidlActivity.this, "远程服务连接成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("mrh", "连接失败");
            }
        };
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void performAdd() {
        if (mAddInterface != null) {
            int result = mAddInterface.add(1, 2);
            Toast toast = Toast.makeText(AidlActivity.this, "1 + 2 = " + result, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
    }
}
