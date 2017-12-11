package mds.dogwalker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("Estamos no Receiver.", "Oba!");

        // pega a string extra do intent
        String pega_string = intent.getExtras().getString("extra");

        // cria um intent para o serviço de ringtone
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        service_intent.putExtra("extra", pega_string);

        // Inicia o ringtone service
        context.startService(service_intent);

    }
}
