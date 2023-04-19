package com.example.routeranger;

import static org.junit.Assert.assertEquals;

import com.example.routeranger.model.Route;

import org.junit.Before;
import org.junit.Test;

public class RouteTest {

    private Route route;

    @Before
    public void setUp() {
        route = new Route();
    }

    @Test
    public void testUid() {
        int uid = 1;
        route.uid = uid;
        assertEquals(uid, route.uid);
    }

    @Test
    public void testName() {
        String name = "Test Route";
        route.name = name;
        assertEquals(name, route.name);
    }

    @Test
    public void testStart() {
        String start = "Point A";
        route.start = start;
        assertEquals(start, route.start);
    }

    @Test
    public void testEnd() {
        String end = "Point B";
        route.end = end;
        assertEquals(end, route.end);
    }

    @Test
    public void testLength() {
        int length = 10;
        route.length = length;
        assertEquals(length, route.length);
    }

    @Test
    public void testDuration() {
        String duration = "30 minutes";
        route.duration = duration;
        assertEquals(duration, route.duration);
    }

    @Test
    public void testUserId() {
        int userId = 5;
        route.userId = userId;
        assertEquals(userId, route.userId);
    }
}
