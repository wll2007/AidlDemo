package com.aidl.wangll.aidldemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class clientService extends Service {
    private String TAG = clientService.class.getName();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent1 = new Intent();
        intent1.setAction("com.wangll.service");
        intent1.setPackage("com.aidl.wangll.receive");
        bindService(intent1,connection,BIND_AUTO_CREATE);
        return START_STICKY;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGetUserInterface iGetUserInterface = IGetUserInterface.Stub.asInterface(iBinder);
            User user = new User();
            try {
                iGetUserInterface.getUser(user);
                Log.i(TAG,user.getName()+user.getAge());
                user = new User("3244",900);
                iGetUserInterface.setUser(user);
                iGetUserInterface.registerLisenter(lisenter);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    private ILisenterInterface lisenter = new ILisenterInterface.Stub() {
        @Override
        public void ReceiveMsg(User user) throws RemoteException {
            Log.i("ILisenterInterface",Thread.currentThread().getName());
            Log.i("ILisenterInterface",user.getName()+"_____"+user.getAge());
        }
    };
}
