package com.example.routeranger.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.model.Dao.UserDao;
import com.example.routeranger.model.User;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class SettingsViewModel extends ViewModel {

    private AppDatabase db;

    private User user;

    private UserDao userDao;

    public String initName, initPass, initLoc;

    SettingsViewModel(AppDatabase appDatabase) { this.db = appDatabase; }

    private static final String TAG = "SettingsViewModel";

    public void populateFields() {
        userDao = db.userDao();

        Disposable d = userDao.findById(db.loggedInUserId)
                .subscribeWith(new DisposableSingleObserver<User>() {

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(User mUser) {
                user = mUser;
                initName = user.mUsername;
                initPass = user.mPassword;
                initLoc = user.location;
            }
        });
    }

    public void updateSettings(String username, String password, String location) {
        user.mUsername = username;
        user.mPassword = password;
        user.location = location;
        Disposable d = userDao.updateUser(user)
                .subscribeWith(new DisposableCompletableObserver() {

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onStart() {
                Log.i(TAG, "User " + user.mUsername + " is being updated...");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "User " + user.mUsername + " is updated!");
            }
        });
    }

}
