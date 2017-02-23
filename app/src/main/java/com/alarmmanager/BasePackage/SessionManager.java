package com.alarmmanager.BasePackage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Giri on 3/15/2016.
 */
public class SessionManager {
    // Shared Preferences
    SharedPreferences preferences;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    SharedPreferences PersistentPreferences;
    SharedPreferences.Editor editorPersistent;
    /*****
     * persistent pref
     ****/
    private static final String POLICY_PREFS_NAME = "PolicyPrefsFile";
    //Application context
    Context context;
    private static final String PREFS_NAME = "PrefsFile";


    public SessionManager(Context applicationContext) {
        this.context = applicationContext;
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.apply();
        PersistentPreferences = context.getSharedPreferences(POLICY_PREFS_NAME, Context.MODE_PRIVATE);
        editorPersistent = PersistentPreferences.edit();
        editorPersistent.apply();
    }
}
