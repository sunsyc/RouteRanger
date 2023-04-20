package com.example.routeranger.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.R;
import com.example.routeranger.model.User;
import com.example.routeranger.model.Dao.UserDao;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private AppDatabase db;

    private static final String TAG = "LoginViewModel";

    LoginViewModel(AppDatabase appDatabase) {
        this.db = appDatabase;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {

        UserDao userDao = db.userDao();

        ListenableFuture<User> future = userDao.findByCredentials(username, password);
        Futures.addCallback(
                future,
                new FutureCallback<User>() {
                    @Override
                    public void onSuccess(User result) {
                        if (result != null) {
                            db.loggedInUserId = result.uid;
                            Log.i(TAG, "User " + result.mUsername + " is logging in!");
                        } else {
                            User newUser = new User(username, password);
                            Log.i(TAG, "User " + newUser.mUsername + " is being created!");
                            ListenableFuture<Long> future1 = userDao.insert(newUser);
                            Futures.addCallback(
                                    future1,
                                    new FutureCallback<Long>() {
                                        @Override
                                        public void onSuccess(Long result) {
                                            db.loggedInUserId = result.intValue();
                                            Log.i(TAG, "User " + newUser.mUsername + " is in the database!");
                                        }

                                        @Override
                                        public void onFailure(Throwable t) {
                                            t.printStackTrace();
                                        }
                                    }, db.dbWriteExecutor
                            );
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }, db.dbWriteExecutor
        );
        loginResult.setValue(new LoginResult(new LoggedInUserView(username + password)));
    }
        public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}