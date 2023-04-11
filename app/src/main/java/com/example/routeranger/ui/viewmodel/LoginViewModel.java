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

        Disposable d = userDao.findByCredentials(username, password)
                .subscribeWith(new DisposableSingleObserver<User>() {

           @Override
           public void onError(Throwable e) {
               e.printStackTrace();
           }

           @Override
           public void onSuccess(User user) {
               if (user == null) {
                   User newUser = new User(username, password);
                   Disposable d1 = userDao.insertAll(newUser)
                           .subscribeWith(new DisposableCompletableObserver() {

                       @Override
                       public void onError(Throwable e) {
                           e.printStackTrace();
                       }

                       @Override
                       public void onStart() {
                            Log.i(TAG, "User " + newUser.mUsername + " is being created...");
                       }

                       @Override
                       public void onComplete() {
                            db.loggedInUserId = newUser.uid;
                            Log.i(TAG, "User " + newUser.mUsername + " is logged in!");
                       }
                   });
               } else {
                   loginResult.setValue(new LoginResult(new LoggedInUserView(user.mUsername + user.mPassword)));
                   Log.i(TAG, "User " + user.mUsername + " already exists!");
                   db.loggedInUserId = user.uid;
                   Log.i(TAG, "User " + user.mUsername + " is logged in!");
               }
           }
        });
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