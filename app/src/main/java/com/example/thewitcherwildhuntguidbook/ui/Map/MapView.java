package com.example.thewitcherwildhuntguidbook.ui.Map;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MapView extends androidx.appcompat.widget.AppCompatImageView {

    private final ScaleGestureDetector scaleGestureDetector;
    private float factor = 1f;
    private float maxScale = 8f;
    private final PointF currentPosition = new PointF(0.0f, 0.0f);
    private final Point screenSize = new Point(0,0);
    private final PointF centerPosition = new PointF(0,0);
    private final PointF offset = new PointF(0.0f, 0.0f);
    private int point;

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        private final MapView mapImage;

        ScaleListener(MapView mapImage) {
            this.mapImage = mapImage;
        }

        public boolean onScale(ScaleGestureDetector detector) {
            float lastFactor = factor;
            float tmpX = getX() - centerPosition.x;
            float tmpY = getY() - centerPosition.y;

            factor *= detector.getScaleFactor();
            factor = Math.max(0.5f, Math.min(factor, maxScale));

            mapImage.setScaleX(factor);
            mapImage.setScaleY(factor);

            mapImage.setX(tmpX * (factor / lastFactor) + centerPosition.x);
            mapImage.setY(tmpY * (factor / lastFactor) + centerPosition.y);
            return true;
        }
    }

    public MapView(@NonNull Context context) {
        super(context);
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener(this));
        scaleGestureDetector.setQuickScaleEnabled(false);
        screenSize.set(
                getResources().getDisplayMetrics().widthPixels / 2,
                getResources().getDisplayMetrics().heightPixels / 2);
        setCenterPosition(this);
        this.setScaleX(factor);
        this.setScaleY(factor);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener(this));
        scaleGestureDetector.setQuickScaleEnabled(false);
        screenSize.set(
                getResources().getDisplayMetrics().widthPixels / 2,
                getResources().getDisplayMetrics().heightPixels / 2);
        setCenterPosition(this);
        this.setScaleX(factor);
        this.setScaleY(factor);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener(this));
        scaleGestureDetector.setQuickScaleEnabled(false);
        screenSize.set(
                getResources().getDisplayMetrics().widthPixels / 2,
                getResources().getDisplayMetrics().heightPixels / 2);
        setCenterPosition(this);
        this.setScaleX(factor);
        this.setScaleY(factor);
    }

    private void setCenterPosition(MapView mapImage){
        post(new Runnable() {
            @Override
            public void run() {
                centerPosition.set(mapImage.getX(), mapImage.getY() - 20);
            }
        });
    }

    public void scroll(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                point = motionEvent.getPointerId(0);
                currentPosition.set(motionEvent.getRawX(), motionEvent.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                if(point == motionEvent.getPointerId(0)) {
                    offset.set(motionEvent.getRawX() - currentPosition.x, motionEvent.getRawY() - currentPosition.y);
                    if(getCurrentHeight() < 2 * screenSize.y) {
                        if (getY() - (getCurrentHeight() / 2) < (-screenSize.y + centerPosition.y) && offset.y < 0)
                            offset.y = -0.5f;
                        if (getY() + (getCurrentHeight() / 2) > (screenSize.y + centerPosition.y) && offset.y > 0)
                            offset.y = 0.5f;
                    }
                    else {
                        if (getY() - (getCurrentHeight() / 2) > (-screenSize.y + centerPosition.y) && offset.y > 0)
                            offset.y = 0.5f;
                        if (getY() + (getCurrentHeight() / 2) < (screenSize.y + centerPosition.y) && offset.y < 0)
                            offset.y = -0.5f;
                    }
                    if(getWidth() * factor < 2 * screenSize.x) {
                        if (getX() - (getCurrentWidth() / 2) < (-screenSize.x + centerPosition.x) && offset.x < 0)
                            offset.x = -0.5f * Math.abs((-screenSize.x + centerPosition.x) / (getX() - (getCurrentWidth() / 2)));
                        if (getX() + (getCurrentWidth() / 2) > (screenSize.x + centerPosition.x) && offset.x > 0)
                            offset.x = 0.5f;
                    }
                    else {
                        if (getX() - (getCurrentWidth() / 2) > (-screenSize.x + centerPosition.x) && offset.x > 0)
                            offset.x = 0.5f;
                        if (getX() + (getCurrentWidth() / 2) < (screenSize.x + centerPosition.x) && offset.x < 0)
                            offset.x = -0.5f;
                    }
                    setX(getX() + offset.x);
                    setY(getY() + offset.y);
                }
                else {
                    point = motionEvent.getPointerId(0);
                }
                currentPosition.set(motionEvent.getRawX(), motionEvent.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                if(getCurrentHeight() < 2 * screenSize.y) {
                    if (getY() - (getCurrentHeight() / 2) < (-screenSize.y + centerPosition.y))
                        animate().y(-screenSize.y + centerPosition.y + (getCurrentHeight() / 2)).setDuration(100);
                    if (getY() + (getCurrentHeight() / 2) > (screenSize.y + centerPosition.y))
                        animate().y(screenSize.y + centerPosition.y - (getCurrentHeight() / 2)).setDuration(100);
                }
                else {
                    if (getY() - (getCurrentHeight() / 2) > (-screenSize.y + centerPosition.y))
                        animate().y(-screenSize.y + centerPosition.y + (getCurrentHeight() / 2)).setDuration(100);
                    if (getY() + (getCurrentHeight() / 2) < (screenSize.y + centerPosition.y))
                        animate().y(screenSize.y + centerPosition.y - (getCurrentHeight() / 2)).setDuration(100);
                }
                if(getCurrentWidth() < 2 * screenSize.x) {
                    if (getX() - (getCurrentWidth() / 2) < (-screenSize.x + centerPosition.x))
                        animate().x(-screenSize.x + centerPosition.x + (getCurrentWidth() / 2)).setDuration(100);
                    if (getX() + (getCurrentWidth() / 2) > (screenSize.x + centerPosition.x))
                        animate().x(screenSize.x + centerPosition.x -(getCurrentWidth() / 2)).setDuration(100);
                }
                else {
                    if (getX() - (getCurrentWidth() / 2) > (-screenSize.x + centerPosition.x))
                        animate().x(-screenSize.x + centerPosition.x + (getCurrentWidth() / 2)).setDuration(100);
                    if (getX() + (getCurrentWidth() / 2) < (screenSize.x + centerPosition.x))
                        animate().x(screenSize.x + centerPosition.x -(getCurrentWidth() / 2)).setDuration(100);
                }
                break;
        }
    }

    public void scaleSize(float factor){
        float lastFactor = this.factor;
        float tmpX = getX() - centerPosition.x;
        float tmpY = getY() - centerPosition.y;
        this.factor = factor;
        animate().scaleY(factor).setDuration(300);
        animate().scaleX(factor).setDuration(300);
        animate().x(tmpX * (factor / lastFactor) + centerPosition.x).setDuration(300);
        animate().y(tmpY * (factor / lastFactor) + centerPosition.y).setDuration(300);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void scaleSize(MotionEvent motionEvent)
    {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if(factor < 1.0f) {
                factor = 1.0f;
                animate().scaleY(factor).setDuration(300);
                animate().scaleX(factor).setDuration(300);
                animate().x(centerPosition.x).setDuration(300);
                animate().y(centerPosition.y).setDuration(300);
            }
            else if (factor > maxScale * 0.8f) {
                scaleSize(maxScale * 0.8f);
            }
        }
        else {
            scaleGestureDetector.onTouchEvent(motionEvent);
            int i = 0;
            while (i < motionEvent.getPointerCount()) {
                if (motionEvent.getPointerId(i) == point)
                    break;
                i++;
            }
            if (i == motionEvent.getPointerCount()) {
                point = motionEvent.getPointerId(0);
                i = 0;
            }
            currentPosition.set(motionEvent.getRawX(i), motionEvent.getRawY(i));
        }
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public float getCurrentWidth() {
        return this.getWidth() * factor;
    }

    public float getCurrentHeight() {
        return this.getHeight() * factor;
    }
}