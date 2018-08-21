package com.haha.myself.mvvm;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.haha.myself.mvvm.User;

/**
 * @author xj
 * Created by xj on 2018/8/3.
 */
public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepo;

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId){
        if(this.user != null)
            return;

        user = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void modifyUser(String userId){
        userRepo.modifyUser(userId);
    }

}
