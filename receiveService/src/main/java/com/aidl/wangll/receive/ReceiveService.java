package com.aidl.wangll.receive;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aidl.wangll.aidldemo.IGetUserInterface;
import com.aidl.wangll.aidldemo.ILisenterInterface;
import com.aidl.wangll.aidldemo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class ReceiveService extends Service {
    private String TAG = ReceiveService.class.getName();
    private List<ILisenterInterface> lists = new ArrayList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    private IBinder mIBinder = new IGetUserInterface.Stub() {
        @Override
        public void getUser(User name) throws RemoteException {
            name.setAge(78);
            name.setName("wewew");
        }

        @Override
        public void setUser(User user) throws RemoteException {
            Log.i(TAG,user.getName()+user.getAge());
        }

        @Override
        public void registerLisenter(ILisenterInterface lisenter) throws RemoteException {
            lists.add(lisenter);
            Log.i(TAG,Thread.currentThread().getName());
            handler.sendEmptyMessage(0);
        }

        @Override
        public void unregisterLisenter(ILisenterInterface lisenter) throws RemoteException {

        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG,Thread.currentThread().getName());
            super.handleMessage(msg);
            User user = new User("大大",6666);
            for (int i = 0; i < lists.size(); i++) {
                try {
                    lists.get(i).ReceiveMsg(user);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            handler.sendEmptyMessageDelayed(0,1000);
        }
    };

}
