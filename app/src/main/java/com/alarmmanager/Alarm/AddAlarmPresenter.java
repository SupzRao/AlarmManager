package com.alarmmanager.Alarm;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alarmmanager.BasePackage.BasePresenter;
import com.alarmmanager.BasePackage.MyApplication;
import com.alarmmanager.BasePackage.SessionManager;
import com.alarmmanager.MainActivity;
import com.alarmmanager.model.dao.AlarmDao;
import com.alarmmanager.model.entity.Alarm;

/**
 * Created by Suprada on 23-Feb-17.
 */

public class AddAlarmPresenter extends BasePresenter<AddAlarmActivity> {
    Context context;
    AddAlarmActivity addAlarmActivity;

    public AddAlarmPresenter() {
        super();
    }

    public AddAlarmPresenter(Context context) {
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    protected void onTakeView(AddAlarmActivity view) {
        super.onTakeView(view);
        addAlarmActivity = view;
    }

    public void addAlarmDataIntoDb(Alarm alarm, int key_type) {
        AlarmDao alarmDao = MyApplication.getDatabaseManager().GetSession().getAlarmDao();
        /* inset into sqlite db*/
        alarmDao.insert(alarm);
        Toast.makeText(addAlarmActivity, "Saved Successfully ", Toast.LENGTH_SHORT).show();
        if (key_type == 0) {
            Intent gobacktolist = new Intent(addAlarmActivity, MainActivity.class);
            addAlarmActivity.startActivity(gobacktolist);
        }else
        {

        }
    }
}
