package br.com.cesaredu.smartcar.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import br.com.cesaredu.smartcar.broadcasts.AmarinoReceiver;
import br.com.cesaredu.smartcar.utils.FlagsUtil;
import br.com.cesaredu.smartcar.utils.JoyStick;

import at.abraxas.amarino.Amarino;
import br.com.cesaredu.smartcar.R;

/**
 * Created by CassioPaixao on 25/10/2017.
 */
public class MainActivity extends AppCompatActivity implements JoyStick.JoyStickListener, View.OnTouchListener, AmarinoReceiver.DistanceListener {
    public static final String BLUETOOTH_MAC_ADDRESS = "20:16:10:24:30:84";
    private TextView textView;
    private boolean togglePower = false;
    private int speed = 0;
    private int direction = -1;
    private int servo_pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.btn_a).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((ToggleButton) v).isChecked()) {
                    findViewById(R.id.btn_a).setBackgroundResource(R.drawable.shapekeypress);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_a).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#191919"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
//                    gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

                }else {
                    findViewById(R.id.btn_a).setBackgroundResource(R.drawable.shape);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_a).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
                }

                return false;
            }
        });
        findViewById(R.id.btn_b).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((ToggleButton) v).isChecked()) {
                    findViewById(R.id.btn_b).setBackgroundResource(R.drawable.shapekeypress);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_b).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#191919"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
//                    gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

                }else {
                    findViewById(R.id.btn_b).setBackgroundResource(R.drawable.shape);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_b).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
                }

                return false;
            }
        });
        findViewById(R.id.btn_l1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((ToggleButton) v).isChecked()) {
                    findViewById(R.id.btn_l1).setBackgroundResource(R.drawable.shapekeypress);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_l1).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#191919"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
//                    gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

                }else {
                    findViewById(R.id.btn_l1).setBackgroundResource(R.drawable.shape);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_l1).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
                }

                return false;
            }
        });
        findViewById(R.id.btn_power).setOnTouchListener(this);
        findViewById(R.id.btn_r1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((ToggleButton) v).isChecked()) {
                    findViewById(R.id.btn_r1).setBackgroundResource(R.drawable.shapekeypress);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_r1).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#191919"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
//                    gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

                }else {
                    findViewById(R.id.btn_r1).setBackgroundResource(R.drawable.shape);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_r1).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
                }

                return false;
            }
        });
        findViewById(R.id.btn_x).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((ToggleButton) v).isChecked()) {
                    findViewById(R.id.btn_x).setBackgroundResource(R.drawable.shapekeypress);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_x).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#191919"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
//                    gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

                }else {
                    findViewById(R.id.btn_l1).setBackgroundResource(R.drawable.shape);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_x).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
                }

                return false;
            }
        });
        findViewById(R.id.btn_y).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((ToggleButton) v).isChecked()) {
                    findViewById(R.id.btn_y).setBackgroundResource(R.drawable.shapekeypress);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_y).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#191919"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
//                    gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

                }else {
                    findViewById(R.id.btn_y).setBackgroundResource(R.drawable.shape);
                    @SuppressLint("WrongViewCast") GradientDrawable gd = (GradientDrawable) findViewById(R.id.btn_y).getBackground().getCurrent();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadii(new float[]{100, 100, 100, 100, 100, 100, 100, 100});
                }

                return false;
            }
        });
        JoyStick joyMove = (JoyStick) findViewById(R.id.joy1);
        joyMove.setListener(this);
        joyMove.setPadBackground(R.drawable.pad);
        joyMove.setButtonDrawable(R.drawable.button);
        joyMove.setType(JoyStick.TYPE_4_AXIS);

        JoyStick joyRotate = (JoyStick) findViewById(R.id.joy2);
        joyRotate.setListener(this);
        joyRotate.setPadBackground(R.drawable.pad);
        joyRotate.setButtonDrawable(R.drawable.button);
        joyRotate.setType(JoyStick.TYPE_2_AXIS_LEFT_RIGHT);
        joyRotate.enableStayPut(true);
        AmarinoReceiver.setDistanceListener(this);
    }

    @Override
    public void onMove(JoyStick joyStick, int speed, int direction) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                this.speed = speed;
                this.direction = direction;
                textView.setText("Velocidade: " + speed + "\nDirecao: " + direction);
                break;
            case R.id.joy2:
                servo_pos = speed;
                textView.setText("Velocidade: " + speed);
                Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.SERVO, speed);
                break;
        }
    }

    @Override
    public void onDistanceReceived(int distance) {
        textView.setText("Distância (cm): " + distance);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            switch(v.getId()) {
                case R.id.btn_power:
                    togglePower = !togglePower;
                    if (togglePower) {
                        textView.setText("connecting...");
                        Amarino.connect(getApplicationContext(), BLUETOOTH_MAC_ADDRESS);
                    } else {
                        textView.setText("disconnecting...");
                        Amarino.disconnect(getApplicationContext(), BLUETOOTH_MAC_ADDRESS);
                    }
                    return true;
                case R.id.btn_a:
                    char flag = FlagsUtil.PARAR;
                    if (this.direction == JoyStick.DIRECTION_UP) {
                        flag = FlagsUtil.FRENTE;
                    } else if (this.direction == JoyStick.DIRECTION_DOWN) {
                        flag = FlagsUtil.TRAS;
                    } else if (this.direction == JoyStick.DIRECTION_RIGHT) {
                        flag = FlagsUtil.DIREITA;
                    } else if (this.direction == JoyStick.DIRECTION_LEFT) {
                        flag = FlagsUtil.ESQUERDA;
                    }
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, flag, this.speed);
                    return true;
                case R.id.btn_b:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.DIST, 1);
                    return true;
                case R.id.btn_l1:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.LED_F, 1);
                    return true;
                case R.id.btn_r1:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.LED_T, 1);
                    return true;
                case R.id.btn_x:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.BUZ, 1);
                    return true;
            }
        } else if (action == MotionEvent.ACTION_UP) {
            switch(v.getId()) {
                case R.id.btn_a:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.PARAR, 0);
                    return true;
                case R.id.btn_x:
                    Amarino.sendDataToArduino(getApplicationContext(), BLUETOOTH_MAC_ADDRESS, FlagsUtil.BUZ, 1);
                    return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        Amarino.disconnect(getApplicationContext(), BLUETOOTH_MAC_ADDRESS);
        super.onDestroy();
    }

    private void DisplayToast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}