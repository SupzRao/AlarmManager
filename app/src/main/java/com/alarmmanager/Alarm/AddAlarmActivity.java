package com.alarmmanager.Alarm;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alarmmanager.BasePackage.BaseActivity;
import com.alarmmanager.R;
import com.alarmmanager.model.entity.Alarm;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

import static com.alarmmanager.BasePackage.BaseFunction.showTime;

@RequiresPresenter(AddAlarmPresenter.class)
public class AddAlarmActivity extends BaseActivity<AddAlarmPresenter> {
    @BindView(R.id.tv_set_alarm_tone)
    TextView tv_set_alarm_tone;
    @BindView(R.id.tv_display_time)
    TextView tv_display_time;
    private String chosenRingtone;
    @BindView(R.id.ll_alarm_tone)
    LinearLayout ll_alarm_tone;
    Uri defaultRintoneUri;
    Ringtone defaultRingtone;
    @BindView(R.id.timepicker)
    TimePicker timepicker;
    String AMPM;
    Calendar calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(AddAlarmActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.vt_arrow_back);
        AMPM = "AM";
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAlarmActivity.this.onBackPressed();
            }
        });
        defaultRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE);
        defaultRingtone = RingtoneManager.getRingtone(this, defaultRintoneUri);
        tv_set_alarm_tone.setText("" + defaultRingtone.getTitle(this));
        ll_alarm_tone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
                startActivityForResult(intent, 5);
            }
        });
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        tv_display_time.setText(showTime(hour, min));
        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                tv_display_time.setText(showTime(hourOfDay, minute));
            }
        });
    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == 5) {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null) {
                defaultRingtone = RingtoneManager.getRingtone(this, uri);
                chosenRingtone = defaultRingtone.getTitle(this);
                tv_set_alarm_tone.setText(chosenRingtone);
            } else {
                chosenRingtone = defaultRingtone.getTitle(this);
                tv_set_alarm_tone.setText(chosenRingtone);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);
        menu.getItem(0).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Alarm alarm = insert_AlarmData();
            getPresenter().addAlarmDataIntoDb(alarm);

        }
        return true;

    }

    private Alarm insert_AlarmData() {
        Alarm alarm = new Alarm();
        alarm.setTime(tv_display_time.getText().toString());
        alarm.setTone(tv_set_alarm_tone.toString());
        return alarm;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
