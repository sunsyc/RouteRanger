package com.example.routeranger.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.routeranger.model.AppDatabase;
import com.example.routeranger.model.Dao.UserDao;
import com.example.routeranger.model.User;

public class SettingsViewModel extends ViewModel {

    private AppDatabase db;

    private User user;

    private UserDao userDao;

    public String initName, initPass, initLoc;

    SettingsViewModel(AppDatabase appDatabase) { this.db = appDatabase; }

    private static final String TAG = "SettingsViewModel";

    public void populateFields() {
        userDao = db.userDao();
        userDao.findById(db.loggedInUserId).observeForever(response -> {
            user = response;
            initName = user.mUsername;
            initPass = user.mPassword;
            initLoc = user.location;
        });
    }

    public void updateSettings(String username, String password, String location) {
        user.mUsername = username;
        user.mPassword = password;
        user.location = location;
        userDao.updateUser(user);
    }

}
