package com.alarmmanager.Alarm;

import android.content.Context;
import android.os.Bundle;

import com.alarmmanager.BasePackage.BasePresenter;
import com.alarmmanager.BasePackage.MyApplication;
import com.alarmmanager.model.entity.Alarm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Suprada on 23-Feb-17.
 */

public class NonRepeatAlarmListPresenter extends BasePresenter<NonRepeatAlarm> {

    Context context;

    public NonRepeatAlarmListPresenter() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    public NonRepeatAlarmListPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void onTakeView(NonRepeatAlarm view) {
        super.onTakeView(view);
    }

    public List<Alarm> getAlarmDataFromDb() {
        List<Alarm> alarms = MyApplication.getDatabaseManager().GetSession().getAlarmDao()
                .queryBuilder().list();
        Collections.sort(alarms, new Comparator<Alarm>() {
            public int compare(Alarm o1, Alarm o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        return alarms;
    }

}
