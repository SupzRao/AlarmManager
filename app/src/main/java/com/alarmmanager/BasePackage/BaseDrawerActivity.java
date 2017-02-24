package com.alarmmanager.BasePackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alarmmanager.MainActivity;
import com.alarmmanager.R;

import java.util.HashMap;

import nucleus.factory.RequiresPresenter;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;
import timber.log.Timber;

import static com.alarmmanager.BasePackage.MyApplication.createBackStack;

@RequiresPresenter(BaseDrawerPresenter.class)
public class BaseDrawerActivity<PresenterType extends Presenter> extends NucleusAppCompatActivity<PresenterType>
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mActionBarToolbar;
    private DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    ConnectionCheck checkConnection;
    Context context;
    TextView tv_user_name;
    TextView tv_user_email;
    LinearLayout llNavHeader;
    HashMap<String, String> hashMapUserDetails;

    protected boolean useToolbar() {
        return true;
    }

    public SessionManager sessionManager;


    /* Exit from the app confirmation */
    boolean doubleBackToExitPressedOnce = false;


    public static final int EXTRAS_FROM_TRIPS_LIST_ITEM = 2;


    /**
     * Helper method to allow child classes to opt-out of having the
     * hamburger menu.
     *
     * @return
     */
    protected boolean useDrawerToggle() {
        return true;
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        sessionManager = new SessionManager(this);

        getActionBarToolbar();

        setupNavDrawer();

        setupDrawerMenuUserInfo();

    }


    private void setupDrawerMenuUserInfo() {

        View headerLayout = mNavigationView.inflateHeaderView(R.layout.nav_header_main);


    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);
        checkConnection = new ConnectionCheck();
        context = getApplicationContext();


    }//end OnCreate


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void getSessionManager() {
        sessionManager = new SessionManager(this);
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mActionBarToolbar != null) {
                // Depending on which version of Android you are on the Toolbar or the ActionBar may be
                // active so the a11y description is set here.
                mActionBarToolbar.setNavigationContentDescription("Description");
                //setSupportActionBar(mActionBarToolbar);

                if (useToolbar()) {
                    setSupportActionBar(mActionBarToolbar);
                } else {
                    mActionBarToolbar.setVisibility(View.GONE);
                }

            }
        }

        return mActionBarToolbar;
    }

    private void setupNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            Timber.d("No drawer");
            return;
        }


        // use the hamburger menu
        if (useDrawerToggle()) {
            mToggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, mActionBarToolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(mToggle);
            mToggle.syncState();
        }

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_camera:
                createBackStack(new Intent(this, MainActivity.class), this);
                break;
            case R.id.nav_gallery:
                break;
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            closeNavDrawer();
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            return true;
        }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }
            }
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Want to exit?", Toast.LENGTH_SHORT)
                .show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3500);
    }


    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

}
