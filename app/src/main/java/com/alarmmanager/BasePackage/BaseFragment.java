package com.alarmmanager.BasePackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;
import timber.log.Timber;

/**
 * Created by Suprada on 03-Aug-16.
 */
public class BaseFragment<PresenterType extends Presenter> extends NucleusSupportFragment<PresenterType> {
    public SessionManager sessionManager;


    public BaseFragment() {
        Timber.v("constructor");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.v("onCreate " + savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        // get Current User and Current Vehicle

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.v("onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Timber.v("onSaveInstanceState " + bundle);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.v("onViewCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.v("onDestroyView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.v("onResume");
        if (sessionManager != null) {
            // get Current User and Current Vehicle

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.v("onPause");
    }

}
