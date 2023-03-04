package RoomDatabase;

@Database(entities = {LoginData.class}, version = 1, exportSchema = false)
public abstract class LoginDatabase extends RoomDatabase {
    private static final String DB_NAME = "login_database";
    private static volatile LoginDatabase instance;

    public static synchronized LoginDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static LoginDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                LoginDatabase.class,
                DB_NAME).build();
    }

    public abstract LoginDao loginDao();
}
