package com.miituo.atlaskm.Servicio;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import com.miituo.atlaskm.activities.MainActivity;
import com.miituo.atlaskm.R;

public class backPhotosService extends Service {

    /*public backPhotosService(){
        super("backPhotosService");
    }

    public backPhotosService(String name) {
        super(name);
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.e("onStartCommand","Servicio segundo paso");

        startTimer();
        return Service.START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        Intent broadcastIntent = new Intent("miituo.com.miituo.Servicio");
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public ComponentName startForegroundService(Intent service) {


        startTimer();

        return super.startForegroundService(service);

    }

    /*
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }*/

    /*@Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("onHandleIntent","Servicio background");

        startTimer();

        //stopSelf();
        //postNotif("Fotos subidas correctamente");
    }*/


    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("OnCreate","Servicio iniciado");
    }

    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;
    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        initializeTimerTask();
        //initialize the TimerTask's job

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 5000, 1000); //
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                postNotif("Timer indded");
            }
        };
    }

    private void postNotif(String notifString) {
        /*NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int icon = R.drawable.miituo;

        RemoteMessage.Notification notification = new RemoteMessage.Notification(icon, "Firebase" + Math.random(), System.currentTimeMillis());
        //Notification notification = new Notification(icon, "Firebase" + Math.random(), System.currentTimeMillis());
//		notification.flags |= Notification.FLAG_AUTO_CANCEL;

        Context context = getApplicationContext();
        CharSequence contentTitle = "Background" + Math.random();
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, notifString, contentIntent);
        mNotificationManager.notify(1, notification);*/

        Intent intent;

        intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pending=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noti=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("miituo")
                .setContentText(notifString)
                .setAutoCancel(true)
                .setContentIntent(pending)
                .setSound(sound);
        NotificationManager nmg=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nmg.notify(0,noti.build());

    }
}
