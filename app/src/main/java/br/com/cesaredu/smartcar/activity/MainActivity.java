package br.com.cesaredu.smartcar.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.erz.joysticklibrary.JoyStick;

import br.com.cesaredu.smartcar.R;
import br.com.cesaredu.smartcar.game.GameView;

/**
 * Created by CassioPaixao on 25/10/2017.
 */


public class MainActivity extends AppCompatActivity implements JoyStick.JoyStickListener {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.game);

        JoyStick joy1 = (JoyStick) findViewById(R.id.joy1);
        joy1.setListener(this);
        joy1.setPadBackground(R.drawable.pad);
        joy1.setButtonDrawable(R.drawable.button);
//        joy1.setPadColor(Color.parseColor("#55ffffff"));
//        joy1.setButtonColor(Color.parseColor("#55ff0000"));

        JoyStick joy2 = (JoyStick) findViewById(R.id.joy2);
        joy2.setListener(this);
//        joy2.enableStayPut(true);
        joy2.setPadBackground(R.drawable.pad);
        joy2.setButtonDrawable(R.drawable.button);
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
}