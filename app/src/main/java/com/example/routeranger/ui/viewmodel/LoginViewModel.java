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
        //User user = userDao.findByCredentials(username, password).getValue();

        userDao.findByCredentials(username, password).observeForever(user -> {
            if (user == null) {
                user = new User(username, password);
                User finalUser = user;
                db.dbWriteExecutor.execute(() -> userDao.insertAll(finalUser));
                Log.i(TAG, "New User Created!");
            } else {
                Log.i(TAG, "User already exists!");
            }
            loginResult.setValue(new LoginResult(new LoggedInUserView(user.mUsername + user.mPassword)));
            db.loggedInUserId = user.uid;
            Log.i(TAG, "User logged in!");
        });

//        if (user == null) {
//            user = new User(username, password);
//            User finalUser = user;
//            db.dbWriteExecutor.execute(() -> userDao.insertAll(finalUser));
//            Log.i(TAG, "New User Created!");
//        } else {
//            Log.i(TAG, "User already exists!");
//        }
//        loginResult.setValue(new LoginResult(new LoggedInUserView(user.mUsername + user.mPassword)));
//        Log.i(TAG, "User logged in!");


        // can be launched in a separate asynchronous job
//        Result<LoggedInUser> result = loginRepository.login(username, password);
//
//        if (result instanceof Result.Success) {
//            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }
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