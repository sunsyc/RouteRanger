package RoomDatabase;

public class LoginRepository {
    private static volatile LoginRepository instance;

    private LoginDao loginDao;

    private LoginRepository(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public static LoginRepository getInstance(Context context) {
        if (instance == null) {
            instance = new LoginRepository(
                    LoginDatabase.getInstance(context).loginDao());
        }
        return instance;
    }

    public void saveLoginData(LoginData loginData) {
        loginDao.insert(loginData);
    }

    public LoginData getLoginData(String username) {
        return loginDao.findByUsername(username);
    }
}

