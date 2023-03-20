package com.example.routeranger.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.model.LoginDataSource;
import com.example.routeranger.model.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    Context mContext;

    public LoginViewModelFactory(Context context) {
        mContext = context;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(Room.databaseBuilder(mContext, AppDatabase.class, "db").build());
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}