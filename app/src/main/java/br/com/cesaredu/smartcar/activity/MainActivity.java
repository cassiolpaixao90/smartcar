package br.com.cesaredu.smartcar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.erz.joysticklibrary.JoyStick;

import at.abraxas.amarino.Amarino;
import br.com.cesaredu.smartcar.R;
import br.com.cesaredu.smartcar.game.GameView;

/**
 * Created by CassioPaixao on 25/10/2017.
 */
public class MainActivity extends AppCompatActivity implements JoyStick.JoyStickListener, View.OnTouchListener {
    private GameView gameView;
    private TextView textView;
    public static final String BLUETOOTH_MAC_ADDRESS = "20:16:10:24:30:84";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.game);
        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.btn_a).setOnTouchListener(this);
        findViewById(R.id.btn_b).setOnTouchListener(this);
        findViewById(R.id.btn_l1).setOnTouchListener(this);
        findViewById(R.id.btn_power).setOnTouchListener(this);
        findViewById(R.id.btn_r1).setOnTouchListener(this);
        findViewById(R.id.btn_x).setOnTouchListener(this);
        findViewById(R.id.btn_y).setOnTouchListener(this);
        getObject();
    }

    @Override
    public void onMove(JoyStick joyStick, double angle, double power, int direction) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                gameView.move(angle, power);
                textView.setText("Forca: " + power + "\nDirecao: " + direction);
                break;
            case R.id.joy2:
                gameView.rotate(angle);
                break;
        }
    }

    @Override
    public void onTap() { }

    @Override
    public void onDoubleTap() { }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            switch(v.getId()) {
                case R.id.btn_power:
                    textView.setText("connecting...");
                    Amarino.connect(getApplicationContext(), BLUETOOTH_MAC_ADDRESS);
                    return true;
                case R.id.btn_a:
                    textView.setText("acelerando");
                    return true;
                case R.id.btn_r1:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, 'A', 1);
                    return true;
                case R.id.btn_l1:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, 'B', 1);
                    return true;
            }
        } else if (action == MotionEvent.ACTION_UP) {
            switch(v.getId()) {
                case R.id.btn_a:
                    textView.setText("parou de acelerar");
                    return true;
            }
        }
        return false;
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

    @Override
    protected void onDestroy() {
        Amarino.disconnect(getApplicationContext(), BLUETOOTH_MAC_ADDRESS);
        super.onDestroy();
    }
}