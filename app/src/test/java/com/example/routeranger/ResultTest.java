package com.example.routeranger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.routeranger.model.Result;

import org.junit.Test;

public class ResultTest {

    @Test
    public void testSuccessResult() {
        String data = "Test data";
        Result<String> result = new Result.Success<>(data);

        assertTrue(result instanceof Result.Success);
        assertEquals(data, ((Result.Success<String>) result).getData());
        assertEquals("Success[data=" + data + "]", result.toString());
    }

    @Test
    public void testErrorResult() {
        Exception exception = new Exception("Test exception");
        Result result = new Result.Error(exception);

        assertTrue(result instanceof Result.Error);
        assertEquals(exception, ((Result.Error) result).getError());
        assertEquals("Error[exception=" + exception.toString() + "]", result.toString());
    }
}

