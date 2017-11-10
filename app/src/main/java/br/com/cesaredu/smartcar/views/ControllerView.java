package br.com.cesaredu.smartcar.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import br.com.cesaredu.smartcar.R;

/**
 * Created by CassioPaixao on 24/10/2017.
 */

public class ControllerView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint paint;
    private Bitmap mBitmap;
    private Display display;
    private Canvas canvas;

    private Context context;
    private float eixoX;
    private float eixoY;

    public ControllerView(Context context) {
        this(context, null);
    }

    public ControllerView(Context context, AttributeSet attrs) {
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
        super.draw(canvas);
        if (canvas == null) return;
        canvas.drawBitmap(mBitmap, null, getRectangleWindow(), null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            canvas = this.getHolder().lockCanvas();
            this.draw(canvas);
        } finally {
            if (canvas != null) {
                this.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { }

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
            rectangle = new Rect(0, 0, (int) eixoX, (int) eixoY);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rectangle;
    }
}