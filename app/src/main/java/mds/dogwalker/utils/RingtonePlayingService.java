package mds.dogwalker.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import mds.dogwalker.R;
import mds.dogwalker.activities.AlarmeActivity;


public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // pega os valores extras
        String state = intent.getExtras().getString("extra");


        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId == 1) {
            // Cria instância do meia player
            media_song = MediaPlayer.create(this, R.raw.dog);
            media_song.start();
            this.isRunning = true;
            this.startId = 0;

            // notificação
            NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // seta um intent que vai para o AlarmeActivity
            Intent intent_AlarmeActivity = new Intent(this.getApplicationContext(), AlarmeActivity.class);
            // seta um pending intent
            PendingIntent pending_intent_AlarmeActivity = PendingIntent.getActivity(this, 0,
                    intent_AlarmeActivity, 0);

            // Cria os parametros da notificação
            Notification notificationPopup = new Notification.Builder(this)
                    .setContentTitle("Alarme")
                    .setContentText("Hora do passeio!")
                    .setContentIntent(pending_intent_AlarmeActivity)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true).build();

            // Cria os call commands para a notificação
            assert notifyManager != null;
            notifyManager.notify(0, notificationPopup);

        } else if (this.isRunning && startId == 0){
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = 0;

        } else if (!this.isRunning && startId == 0){
            this.isRunning = false;
            this.startId = 0;

        } else if (this.isRunning && startId == 1) {
            this.isRunning = true;
            this.startId = 1;
        } else {
        }



        return START_NOT_STICKY;
    };

    @Override
    public void onDestroy(){

        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT).show();
    }

}
