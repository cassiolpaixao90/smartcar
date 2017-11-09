package br.com.cesaredu.smartcar.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import at.abraxas.amarino.AmarinoIntent;

public class AmarinoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case AmarinoIntent.ACTION_CONNECTED:
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
                break;
            case AmarinoIntent.ACTION_DISCONNECTED:
                Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
                break;
            case AmarinoIntent.ACTION_CONNECTION_FAILED:
                Toast.makeText(context, "Connection failed", Toast.LENGTH_LONG).show();
                break;
            case "amarino.intent.action.COMMUNICATIONS_HALTED":
                Toast.makeText(context, "Connection halted", Toast.LENGTH_LONG).show();
                break;
            case AmarinoIntent.ACTION_RECEIVED:
                Toast.makeText(context, "Data received", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
