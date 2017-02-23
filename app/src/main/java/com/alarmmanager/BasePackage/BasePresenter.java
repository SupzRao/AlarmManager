package com.alarmmanager.BasePackage;

import android.os.Bundle;

import nucleus.presenter.RxPresenter;
import timber.log.Timber;

/**
 * Created by vkira on 08-03-2016.
 */
public class BasePresenter<ViewType> extends RxPresenter<ViewType> {

    public SessionManager sessionManager;

    public BasePresenter() {
        Timber.v("constructor");
    }

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        sessionManager=new SessionManager(MyApplication.getInstance().getApplicationContext());
        Timber.v("onCreate");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.v("onDestroy");
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Timber.v("onSave");
    }

    @Override
    protected void onTakeView(ViewType view) {
        super.onTakeView(view);
        Timber.v("onTakeView");
    }

    @Override
    protected void onDropView() {
        super.onDropView();
        Timber.v("onDropView");
    }

}
