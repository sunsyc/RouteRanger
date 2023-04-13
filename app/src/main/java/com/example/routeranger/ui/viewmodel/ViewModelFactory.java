package com.example.routeranger.ui.viewmodel;

import android.content.Context;
import android.provider.Settings;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.routeranger.model.AppDatabase;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    Context mContext;

    public ViewModelFactory(Context context) {
        mContext = context;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(AppDatabase.getInstance(mContext));
        } else if (modelClass.isAssignableFrom(SettingsViewModel.class)) {
            return (T) new SettingsViewModel(AppDatabase.getInstance(mContext));
        } else if (modelClass.isAssignableFrom(NewRouteViewModel.class)) {
            return (T) new NewRouteViewModel(AppDatabase.getInstance(mContext));
        }
        else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}