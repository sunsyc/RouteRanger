package RoomDatabase;

@Dao
public interface LoginDao {
    @Query("SELECT * FROM login_data WHERE username = :username")
    LoginData findByUsername(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LoginData loginData);

    @Delete
    void delete(LoginData loginData);
}

