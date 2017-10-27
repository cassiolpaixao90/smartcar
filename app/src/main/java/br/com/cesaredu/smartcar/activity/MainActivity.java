package br.com.cesaredu.smartcar.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.erz.joysticklibrary.JoyStick;

import at.abraxas.amarino.Amarino;
import br.com.cesaredu.smartcar.R;
import br.com.cesaredu.smartcar.broadcasts.AmarinoReceiver;
import br.com.cesaredu.smartcar.game.GameView;
import br.com.cesaredu.smartcar.utils.FlagsUtil;
import butterknife.internal.Utils;

/**
 * Created by CassioPaixao on 25/10/2017.
 */


public class MainActivity extends AppCompatActivity implements JoyStick.JoyStickListener {

    private GameView gameView;
    private AmarinoReceiver amarinoReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.game);
        getObject();
        getAmarinoReceiver();
    }

    @Override
    public void onMove(JoyStick joyStick, double angle, double power, int direction) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                gameView.move(angle, power);
                break;
            case R.id.joy2:
                gameView.rotate(angle);
                break;
        }
    }

    @Override
    public void onTap() {
    }

    @Override
    public void onDoubleTap() {
    }


    public void getObject() {

        JoyStick joyMove = (JoyStick) findViewById(R.id.joy1);
        joyMove.setListener(this);
        joyMove.setPadBackground(R.drawable.pad);
        joyMove.setButtonDrawable(R.drawable.button);

        JoyStick joyRotate = (JoyStick) findViewById(R.id.joy2);
        joyRotate.setListener(this);
        joyRotate.setPadBackground(R.drawable.pad);
        joyRotate.setButtonDrawable(R.drawable.button);
    }

    public void btnR1(View view){

    }
    public void btnL1(View view){

    }
    public void btnPower(View view){
        FlagsUtil.connect(getApplicationContext(), FlagsUtil.BLUETOOTH);
    }
    public void btnA(View view){

    }
    public void btnB(View view){

    }
    public void btnX(View view){

    }
    public void btnY(View view){

    }

    @Override
    protected void onDestroy() {
        if(amarinoReceiver != null){
            unregisterReceiver(amarinoReceiver);
        }
        FlagsUtil.desconnected(getApplicationContext(), FlagsUtil.BLUETOOTH);
        super.onDestroy();
    }

    public void getAmarinoReceiver(){
        this.amarinoReceiver = new AmarinoReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(amarinoReceiver, intentFilter);
    }


}