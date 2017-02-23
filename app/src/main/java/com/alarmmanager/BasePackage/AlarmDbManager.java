package com.alarmmanager.BasePackage;

import android.content.Context;

import com.alarmmanager.model.dao.DaoMaster;
import com.alarmmanager.model.dao.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

public class AlarmDbManager extends DatabaseOpenHelper {

    private DaoMaster daoMaster;
    private DaoSession daoSession;

    /**
     * Instantiate a new DB Helper.
     * <br> SQLiteOpenHelpers are statically cached so they (and their internally cached SQLiteDatabases) will be reused for concurrency
     *
     * @param context Any {@link Context} belonging to your package.
     * @param name    The database name. This may be anything you like. Adding a file extension is not required and any file extension you would like to use is fine.
     * @param version the database version.
     */
    public AlarmDbManager(Context context, String name, int version) {
        super(context, name, version);

        daoMaster = new DaoMaster(this.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    @Override
    public void onCreate(Database db) {
        DaoMaster.createAllTables(db, false);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(db, true);
    }

    public DaoSession GetSession() {
        return daoSession;
    }
}
