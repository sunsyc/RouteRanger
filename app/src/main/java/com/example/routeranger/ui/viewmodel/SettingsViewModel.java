package com.example.routeranger.ui.viewmodel;

import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.model.Dao.UserDao;
import com.example.routeranger.model.User;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class SettingsViewModel extends ViewModel {

    private AppDatabase db;

    private User user;

    private UserDao userDao;

    SettingsViewModel(AppDatabase appDatabase) { this.db = appDatabase; }

    private static final String TAG = "SettingsViewModel";

    public boolean isUserLoggedIn() {
        return db.loggedInUserId > 0;
    }
    public void populateFields(EditText usernameEditText, EditText passwordEditText, EditText locationEditText) {
        userDao = db.userDao();

        ListenableFuture<User> future = userDao.findById(db.loggedInUserId);
        Futures.addCallback(
                future,
                new FutureCallback<User>() {
                    @Override
                    public void onSuccess(User result) {
                        user = result;
                        usernameEditText.setText(user.mUsername);
                        passwordEditText.setText(user.mPassword);
                        locationEditText.setText(user.location);
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

    public void deleteUser() {
        ListenableFuture<Integer> future = userDao.delete(user);
        Futures.addCallback(
                future,
                new FutureCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer result) {
                        db.loggedInUserId = 0;
                        Log.i(TAG, "User " + user.mUsername + " has been deleted!");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }, db.dbWriteExecutor
        );
    }
}
