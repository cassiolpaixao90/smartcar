package br.com.cesaredu.smartcar.game;

import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by CassioPaixao on 24/10/2017.
 */


public class GameLoop extends Thread {
    private static final long FPS = 24;
    private static final long ticksPS = 1000 / FPS;
    private SurfaceView view;
    private boolean running = false;
    private long startTime;
    private long sleepTime;
    private Canvas canvas;

    public GameLoop(SurfaceView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {
            startTime = System.currentTimeMillis();
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception ignore) {}
        }
    }
}