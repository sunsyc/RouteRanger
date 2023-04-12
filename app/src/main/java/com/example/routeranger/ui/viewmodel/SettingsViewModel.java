package com.example.routeranger.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.model.Dao.UserDao;
import com.example.routeranger.model.User;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

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

    public boolean isUserLoggedIn() {
        return db.loggedInUserId > 0;
    }
    public void populateFields() {
        userDao = db.userDao();

        ListenableFuture<User> future = userDao.findById(db.loggedInUserId);
        Futures.addCallback(
                future,
                new FutureCallback<User>() {
                    @Override
                    public void onSuccess(User result) {
                        user = result;
                        initName = user.mUsername;
                        initPass = user.mPassword;
                        initLoc = user.location;
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }, db.dbWriteExecutor
        );
    }

    public void updateSettings(String username, String password, String location) {
        user.mUsername = username;
        user.mPassword = password;
        user.location = location;

        ListenableFuture<Integer> future = userDao.updateUser(user);
        Futures.addCallback(
                future,
                new FutureCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer result) {
                        Log.i(TAG, "User " + user.mUsername + " is updated!");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }, db.dbWriteExecutor
        );
    }

}
