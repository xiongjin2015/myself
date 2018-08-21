package com.haha.myself.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haha.myself.R;

public class UserProfileFragment extends Fragment {

    private static final String UID_KEY = "uid";
    private UserProfileViewModel viewModel;
    private String userId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // String userId = getArguments().getString(UID_KEY);
        userId = "9999999";
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.setUserRepo(new UserRepository());
        viewModel.init(userId);
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //Update UI
                Log.i("XJ", "-----user:-----" + user);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.modifyUser(userId);
    }
}
