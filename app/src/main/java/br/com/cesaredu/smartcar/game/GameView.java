package br.com.cesaredu.smartcar.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.List;
import java.util.Random;

import br.com.cesaredu.smartcar.R;

/**
 * Created by CassioPaixao on 24/10/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private int i;
    private int size = 20;
    private int minSpeed;
    private int maxSpeed;
    private int minRadius;
    private int maxRadius;
    private float width;
    private float height;
    private float posX;
    private float posY;
    private float radius;
    private double angle;
    private double power;
    private double angle2;
    private GameLoop gameLoop;
    private Paint paint;
    private RectF rectF = new RectF();
    private Random random = new Random();
    private Bitmap mBitmap;
    private Display display;

    private Context context;
    private float eixoX;
    private float eixoY;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setFilterBitmap(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.context = context;
        getWindowWidth();
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas == null) return;
        canvas.drawBitmap(mBitmap, null, getRectangleWindow(), null);

        posX -= Math.cos(angle) * power;
        posY += Math.sin(-angle) * power;
        if (posX > width - radius) posX = width - radius;
        if (posX < radius) posX = radius;
        if (posY > height - radius) posY = height - radius;
        if (posY < radius) posY = radius;

        float rotate;
        if (angle2 == 0) rotate = 0;
        else rotate = (float) Math.toDegrees(angle2) - 90;
        canvas.rotate(rotate, posX, posY);
        rectF.set(posX - radius, posY - radius, posX + radius, posY + radius);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop = new GameLoop(this);
        gameLoop.setRunning(true);
        gameLoop.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.width = width;
        this.height = height;
        float min = Math.min(width, height);

        float centerX = width / 2;
        float centerY = height / 2;
        posX = centerX;
        posY = centerY;
        radius = min / 12;
        rectF = new RectF(posX - radius, posY - radius, posX + radius, posY + radius);

        minSpeed = (int) (min / 37);
        maxSpeed = (int) (min / 12);
        minRadius = (int) (min / 250);
        maxRadius = (int) (min / 220);

        if (maxRadius == minRadius) maxRadius += minRadius;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameLoop.setRunning(false);
        gameLoop = null;
    }

    public void move(double angle, double power) {
        this.angle = angle;
        this.power = power;
        Log.i("angle", "move: " + this.angle);
        Log.i("power", "move: " + this.power);
    }

    public void rotate(double angle2) {
        this.angle2 = angle2;
        Log.i("angle", "move: " + this.angle2);
    }

    public void getWindowWidth() {
        this.mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.smart_joystick);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        eixoX = size.x;
        eixoY = size.y;
    }

    public Rect getRectangleWindow() {
        Rect rectangle = null;
        try {
            rectangle = new Rect(0, 0, Integer.valueOf((int) eixoX), Integer.valueOf((int) eixoY));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rectangle;
    }
}