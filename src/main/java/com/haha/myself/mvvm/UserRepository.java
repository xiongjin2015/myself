package com.haha.myself.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Message;

/**
 * @author xj
 * Created by xj on 2018/8/3.
 */
public class UserRepository {

    MyHandler handler = new MyHandler();
    MutableLiveData<User> data = new MutableLiveData<>();

    public MutableLiveData<User> getUser(final String userId) {

        //模拟网络请求
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.obj = userId;
                handler.sendMessage(msg);
            }
        }.start();

        return data;

    }

    public void modifyUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUserNmae("Bob");
        user.setAge(19);
        data.setValue(user);
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            String userId = (String) msg.obj;
            User user = new User();
            user.setUserId(userId);
            user.setUserNmae("Bob");
            user.setAge(18);
            data.setValue(user);
        }
    }
}
