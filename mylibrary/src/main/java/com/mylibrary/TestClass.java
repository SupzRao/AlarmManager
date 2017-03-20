package com.mylibrary;

import android.util.Log;

/**
 * Created by Suprada on 20-Mar-17.
 */
public class TestClass {
    private static final String TAG = "Welcome";

    public TestClass() {
    }

    public static void g(String message) {
        Log.d("Welcome", message);
    }
}