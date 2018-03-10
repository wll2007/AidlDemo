// IGetUserInterface.aidl
package com.aidl.wangll.aidldemo;
import com.aidl.wangll.aidldemo.User;
import com.aidl.wangll.aidldemo.ILisenterInterface;

// Declare any non-default types here with import statements

interface IGetUserInterface {
     void  getUser(out User name);
     void  setUser(in User user);
     void  registerLisenter(ILisenterInterface lisenter);
     void  unregisterLisenter(ILisenterInterface lisenter);
}
