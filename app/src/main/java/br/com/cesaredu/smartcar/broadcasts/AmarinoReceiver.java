package br.com.cesaredu.smartcar.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import at.abraxas.amarino.AmarinoIntent;

public class AmarinoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (AmarinoIntent.ACTION_CONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Desconnected", Toast.LENGTH_LONG).show();
        }
    }
}
