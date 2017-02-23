package com.alarmmanager.BasePackage;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by vimalprakash on 10/01/16.
 */
public class MyApplication extends Application {

    private static Context context;
    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static MyApplication sInstance;
    static AlarmDbManager databaseMgr;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // initialize the singleton
        sInstance = this;
        // after changing DAO pls increase version
        databaseMgr = new AlarmDbManager(getApplicationContext(),"AlarmDB", 1);

    }

    public static Context getContext() {
        return context;
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized MyApplication getInstance() {
        return sInstance;
    }

    /**
     * Enables back navigation for activities that are launched from the NavBar. See
     * {@code AndroidManifest.xml} to find out the parent activity names for each activity.
     *
     * @param intent
     */
    public static void createBackStack(Intent intent, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(activity);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();
        } else {
            activity.startActivity(intent);
            activity.finish();
        }
    }
    public static synchronized AlarmDbManager getDatabaseManager() {
        return databaseMgr;
    }
}
