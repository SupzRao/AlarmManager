package com.alarmmanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.alarmmanager.Alarm.MonthlyAlarm;
import com.alarmmanager.Alarm.NonRepeatAlarm;
import com.alarmmanager.Alarm.RepeatWeeklyAlarm;
import com.alarmmanager.BasePackage.BaseDrawerActivity;

public class MainActivity extends BaseDrawerActivity implements RepeatWeeklyAlarm.OnFragmentInteractionListener,
        NonRepeatAlarm.OnFragmentInteractionListener
        , MonthlyAlarm.OnFragmentInteractionListener, View.OnClickListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set nav drawer selected to first item in list
        MenuItem tripsmenuitem = mNavigationView.getMenu().findItem(R.id.nav_camera);
        tripsmenuitem.setChecked(true);
        mNavigationView.setNavigationItemSelectedListener(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    // NearBy fragment activity
                    return new NonRepeatAlarm().newInstance("Frament " + position + 1, "");
                case 1:
                    // Fav fragment activity
                    return new RepeatWeeklyAlarm().newInstance("Frament " + position + 1, "");
                case 2:
                    // All fragment activity
                    return new MonthlyAlarm().newInstance("Frament " + position + 1, "");
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Non-Repeat";
                case 1:
                    return "Weekly";
                case 2:
                    return "Monthly";
            }
            return null;
        }
    }
}
