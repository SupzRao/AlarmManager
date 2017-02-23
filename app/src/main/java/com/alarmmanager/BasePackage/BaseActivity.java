package com.alarmmanager.BasePackage;

import android.os.Bundle;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Created by vkira on 08-03-2016.
 */
public class BaseActivity<PresenterType extends Presenter> extends NucleusAppCompatActivity<PresenterType>
{

    public SessionManager sessionManager;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        // get Current User and Current Vehicle
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



}
