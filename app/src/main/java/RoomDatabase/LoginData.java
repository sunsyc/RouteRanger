package RoomDatabase;

@Entity(tableName = "login_data")
public class LoginData {
    @PrimaryKey
    @NonNull
    private String username;
    @ColumnInfo(name = "password")
    private String password;

    public LoginData(@NonNull String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

