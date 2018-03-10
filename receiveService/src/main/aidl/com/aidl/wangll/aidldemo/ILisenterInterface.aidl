// ILisenterInterface.aidl
package com.aidl.wangll.aidldemo;
import com.aidl.wangll.aidldemo.User;

// Declare any non-default types here with import statements

interface ILisenterInterface {
    void ReceiveMsg(in User user);
}
