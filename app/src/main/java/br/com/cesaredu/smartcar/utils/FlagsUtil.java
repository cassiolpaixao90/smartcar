package br.com.cesaredu.smartcar.utils;

import android.content.Context;

import at.abraxas.amarino.Amarino;

/**
 * Created by CassioPaixao on 26/10/2017.
 */

public abstract class FlagsUtil {

    public static final char L1 = 'L';
    public static final char R1 = 'R';
    public static final char A = 'A';
    public static final char B = 'B';
    public static final char X = 'X';
    public static final char Y = 'Y';
    public static final String BLUETOOTH_MAC_ADDRESS = "";

    public static void connect(Context context, String bluetooth) {
        try {
            Amarino.connect(context, bluetooth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void desconnected(Context context, String bluetooth) {
        try {
            Amarino.disconnect(context, bluetooth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
