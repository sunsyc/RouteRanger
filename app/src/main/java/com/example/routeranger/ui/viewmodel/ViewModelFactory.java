package com.example.routeranger.ui.viewmodel;

import android.content.Context;

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
            return (T) new LoginViewModel(Room.databaseBuilder(mContext, AppDatabase.class, "db")
                    .fallbackToDestructiveMigration()
                    .build());
        } else if (modelClass.isAssignableFrom(SettingsViewModel.class)) {
            return (T) new SettingsViewModel(Room.databaseBuilder(mContext, AppDatabase.class, "db")
                    .fallbackToDestructiveMigration()
                    .build());
        }
        else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}