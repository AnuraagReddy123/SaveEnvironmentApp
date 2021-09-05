package com.blueticks.saveenvironment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.work.Worker;

import androidx.core.app.NotificationCompat;
import androidx.work.WorkerParameters;

public class NotifyWorker extends Worker {

    private static final String CHANNEL_ID = "SaveEnvironment";
    private static final String CHANNEL_NAME = "SaveElectricity";
    public static final String LOG_TAG = NotifyWorker.class.getSimpleName();

    public NotifyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        sendNotification();// Method to trigger a notification

        return Result.success();
    }

    /** Create and show a notification containing the received push notification
     */
    private void sendNotification() {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentTitle("Save Environment")
                .setContentText("Are all the unused electrical applicances turned off?")
                .setSmallIcon(R.mipmap.ic_launcher);
        manager.notify(1, builder.build());

    }

}
