package com.example.routeranger;

import static org.junit.Assert.assertEquals;

import com.example.routeranger.model.User;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        String username = "testUser";
        String password = "testPassword";
        user = new User(username, password);
    }

    @Test
    public void testConstructor() {
        assertEquals("testUser", user.mUsername);
        assertEquals("testPassword", user.mPassword);
    }

    @Test
    public void testUid() {
        int uid = 1;
        user.uid = uid;
        assertEquals(uid, user.uid);
    }

    @Test
    public void testUsername() {
        String username = "newUsername";
        user.mUsername = username;
        assertEquals(username, user.mUsername);
    }

    @Test
    public void testPassword() {
        String password = "newPassword";
        user.mPassword = password;
        assertEquals(password, user.mPassword);
    }

    @Test
    public void testLocation() {
        String location = "New York";
        user.location = location;
        assertEquals(location, user.location);
    }
}
