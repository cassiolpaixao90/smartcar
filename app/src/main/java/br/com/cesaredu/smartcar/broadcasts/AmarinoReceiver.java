package br.com.cesaredu.smartcar.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import at.abraxas.amarino.AmarinoIntent;

public class AmarinoReceiver extends BroadcastReceiver {
    private static DistanceListener listener = null;

    public static void setDistanceListener(Context context) {
        listener = (DistanceListener)context;
    }

    public interface DistanceListener {
        void onDistanceReceived(int distance);
    }

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
                int data;
                final int dataType = intent.getIntExtra(AmarinoIntent.EXTRA_DATA_TYPE, -1);

                if (dataType == AmarinoIntent.STRING_EXTRA) {
                    try {
                        data = Integer.parseInt(intent.getStringExtra(AmarinoIntent.EXTRA_DATA));
                    } catch (Exception ex) {
                        data = -1;
                    }

                    if (data > 0 && listener != null) {
                        listener.onDistanceReceived(data);
                    }
                }
                break;
        }
    }
}
