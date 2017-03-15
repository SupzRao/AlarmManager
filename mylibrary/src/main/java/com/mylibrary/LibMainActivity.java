package com.mylibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LibMainActivity extends RelativeLayout {

    TextView content;

    public LibMainActivity(Context context) {
        super(context);
        init(context);
    }

    public LibMainActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        View rootView = inflate(context, R.layout.activity_lib_main, this);
        content = (TextView) rootView.findViewById(R.id.content);

    }
}
