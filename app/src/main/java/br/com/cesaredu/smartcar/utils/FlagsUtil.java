package br.com.cesaredu.smartcar.utils;

import android.content.Context;

import at.abraxas.amarino.Amarino;

/**
 * Created by CassioPaixao on 26/10/2017.
 */

public abstract class FlagsUtil {

    public static final String L1 = "L1";
    public static final String R1 = "R1";
    public static final String A = "A";
    public static final String B = "B";
    public static final String X = "X";
    public static final String Y = "Y";
    public static final String BLUETOOTH = "";


    public static void connect(Context context, String bluetooth){
        try {
            Amarino.connect(context, bluetooth);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void desconnected(Context context, String bluetooth){
        try {
            Amarino.disconnect(context, bluetooth);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
