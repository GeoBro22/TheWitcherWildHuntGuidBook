package com.example.thewitcherwildhuntguidbook.ui.Map;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.thewitcherwildhuntguidbook.R;

public class Map extends Fragment {

    // The position from which the swipe starts
    private final PointF startPosition = new PointF(0.0F, 0.0F);
    // Current position when moving
    private final PointF currentPosition = new PointF(0.0F, 0.0F);
    // Difference between the current swipe position and the previous one
    private Point screenSize = new Point(0,0);
    private final PointF offset = new PointF(0.0F, 0.0F);


    //Class for scale processing
    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            FACTOR *= detector.getScaleFactor();
            FACTOR = Math.max(0.8f, Math.min(FACTOR, 3f));
            imageView.setScaleY(FACTOR);
            imageView.setScaleX(FACTOR);
            return true;
        }
    }

    private ScaleGestureDetector scaleGestureDetector;
    //Scaling size
    private float FACTOR = 1.0f;
    //Image of map
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) view.findViewById(R.id.image_map_velen);
        scaleGestureDetector = new ScaleGestureDetector(view.getContext(), new ScaleListener());
        scaleGestureDetector.setQuickScaleEnabled(false);
        screenSize.set(
                view.getResources().getDisplayMetrics().widthPixels / 2,
                (view.getResources().getDisplayMetrics().heightPixels / 2) - 100);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scaleGestureDetector.onTouchEvent(motionEvent);
                scrollImageView(motionEvent);
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if(FACTOR <= 1f) {
                            FACTOR = 1f;
                            imageView.animate().scaleY(1f).setDuration(500);
                            imageView.animate().scaleX(1f).setDuration(500);
                            imageView.animate().x(0).setDuration(500);
                            imageView.animate().y(0).setDuration(500);
                        }
                        break;
                }
                return true;
            }
        });
    }

    protected void scrollImageView(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startPosition.set(motionEvent.getRawX(), motionEvent.getRawY());
                currentPosition.set(startPosition);
                break;
            case MotionEvent.ACTION_MOVE:
                offset.set(motionEvent.getRawX() - currentPosition.x, motionEvent.getRawY() - currentPosition.y);
                if (imageView.getY() < -screenSize.y*(FACTOR / 1.5f) && offset.y < 0)
                    offset.y = 0;
                if (imageView.getX() < -screenSize.x*(FACTOR / 1.5f) && offset.x < 0)
                    offset.x = 0;
                if (imageView.getY() > screenSize.y*(FACTOR / 1.5f) && offset.y > 0)
                    offset.y = 0;
                if (imageView.getX() > screenSize.x*(FACTOR / 1.5f) && offset.x > 0)
                    offset.x = 0;
                imageView.setX(imageView.getX() + offset.x);
                imageView.setY(imageView.getY() + offset.y);
                currentPosition.set(motionEvent.getRawX(), motionEvent.getRawY());
                break;
        }
    }

}