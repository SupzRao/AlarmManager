package com.alarmmanager.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.alarmmanager.BasePackage.MyApplication;
import com.alarmmanager.R;
import com.alarmmanager.model.entity.Alarm;

import java.util.Calendar;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.alarmmanager.BasePackage.BaseFunction.showTime;

/**
 * Created by Suprada on 02-Feb-17.
 */

public class Reciever extends BroadcastReceiver {
    List<Alarm> alarmlists;
    private String cur_time = "";
    private PendingIntent piStop;
    private PendingIntent piAbort;


    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        cur_time = showTime(hour, min);
        alarmlists = MyApplication.getDatabaseManager().GetSession().getAlarmDao()
                .queryBuilder().list();
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context);
        for (int i = 0; i < alarmlists.size(); i++) {
            if (alarmlists.get(i).getTime().equals(cur_time)) {
                Toast.makeText(context, "You are Notified !", Toast.LENGTH_SHORT).show();
                setNotification(context, nb);
            }

        }

    }

    private void setNotification(Context context, NotificationCompat.Builder nb) {
        int requestID = (int) System.currentTimeMillis();
       /* Intent intent = new Intent(context, AlarmActionActivity.class);
        intent.setAction("myString" + requestID);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, 0);*/
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        piStop = PendingIntent.getActivity(context, 1, new Intent(context,
                NonRepeatAlarm.class), 0);
        piAbort = PendingIntent.getActivity(context, 1, new Intent(context,
                NonRepeatAlarm.class), 0);
       /* Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();*/
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification_small)
                .setContentTitle("Alarm Manager")
                .setContentText("Message")
                .setAutoCancel(true)
                .setSound(alarmUri)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Message"))
                .addAction(R.drawable.ic_stop_gray_png, "Stop", piStop)
                .addAction(R.drawable.ic_close_gray_png, "Snooze", piAbort).build();
        // Put the auto cancel notification flag
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
