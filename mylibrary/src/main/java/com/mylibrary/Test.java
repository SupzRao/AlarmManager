package com.mylibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Suprada on 20-Mar-17.
 */

public class Test {
    public void PrintMsg(Context c, String x)
    {
        Toast.makeText(c, x, Toast.LENGTH_SHORT).show();
    }
}