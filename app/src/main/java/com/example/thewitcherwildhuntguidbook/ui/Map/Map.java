package com.example.thewitcherwildhuntguidbook.ui.Map;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thewitcherwildhuntguidbook.R;

public class Map extends Fragment {
    ImageView imageView;
    private ScaleGestureDetector scaleGestureDetector;
    private float FACTOR = 2f;
    private float lastFactor = 1.0f;
    private final PointF currentPosition = new PointF(0.0F, 0.0F);
    private final PointF screenSize = new PointF(0.0F,0.0F);
    private final Point centerPoint = new Point(0,0);
    private final PointF offset = new PointF(0.0F, 0.0F);
    private final PointF scaleTarget = new PointF();
    private int point;
    private float scrollingSpeedBeyondBorders = 0.25f;

    String[] maps = {"Velen", "White Garden", "Skellige", "Kaer Morhen", "Tussent"};
    Spinner spinnerMaps;

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (detector.getTimeDelta() == 0) {
                lastFactor = FACTOR;
                scaleTarget.set(imageView.getX() - centerPoint.x, imageView.getY() - centerPoint.y);
            }
            FACTOR *= detector.getScaleFactor();
            FACTOR = Math.max(0.5f, Math.min(FACTOR, 10f));

            imageView.setScaleX(FACTOR);
            imageView.setScaleY(FACTOR);

            imageView.setX(scaleTarget.x * (FACTOR / lastFactor) + centerPoint.x);
            imageView.setY(scaleTarget.y * (FACTOR / lastFactor) + centerPoint.y);
            return true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerMaps = (Spinner) view.findViewById(R.id.spinner_map);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner, maps);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerMaps.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spinnerMaps.setOnItemSelectedListener(itemSelectedListener);

        imageView = (ImageView) view.findViewById(R.id.image_map_velen);
        screenSize.set(
                view.getResources().getDisplayMetrics().widthPixels/2,
                view.getResources().getDisplayMetrics().heightPixels/2);
        imageView.setScaleX(FACTOR);
        imageView.setScaleY(FACTOR);
        scaleGestureDetector = new ScaleGestureDetector(view.getContext(), new ScaleListener());
        scaleGestureDetector.setQuickScaleEnabled(false);

        view.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getPointerCount() > 1) {
                    scaleGestureDetector.onTouchEvent(motionEvent);
                    int i = 0;
                    while (i < motionEvent.getPointerCount()) {
                        if(motionEvent.getPointerId(i) == point)
                            break;
                        i++;
                    }
                    if(i == motionEvent.getPointerCount()) {
                        point = motionEvent.getPointerId(0);
                        i = 0;
                    }
                    currentPosition.set(motionEvent.getRawX(i), motionEvent.getRawY(i));
                }
                else {
                    scrollImageView(motionEvent);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (FACTOR < 2f) {
                        FACTOR = 2f;
                        imageView.animate().scaleY(FACTOR).setDuration(300);
                        imageView.animate().scaleX(FACTOR).setDuration(300);
                        imageView.animate().x(centerPoint.x).setDuration(300);
                        imageView.animate().y(centerPoint.y).setDuration(300);
                    }
                    else {
                        if(imageView.getHeight() * FACTOR < 2 * screenSize.y) {
                            if (imageView.getY() - (imageView.getHeight() * FACTOR / 2) < (-screenSize.y + centerPoint.y))
                                imageView.animate().y(-screenSize.y + centerPoint.y + (imageView.getHeight() * FACTOR / 2)).setDuration(100);
                            if (imageView.getY() + (imageView.getHeight() * FACTOR / 2) > (screenSize.y + centerPoint.y))
                                imageView.animate().y(screenSize.y + centerPoint.y - (imageView.getHeight() * FACTOR / 2)).setDuration(100);
                        }
                        else {
                            if (imageView.getY() - (imageView.getHeight() * FACTOR / 2) > (-screenSize.y + centerPoint.y))
                                imageView.animate().y(-screenSize.y + centerPoint.y + (imageView.getHeight() * FACTOR / 2)).setDuration(100);
                            if (imageView.getY() + (imageView.getHeight() * FACTOR / 2) < (screenSize.y + centerPoint.y))
                                imageView.animate().y(screenSize.y + centerPoint.y - (imageView.getHeight() * FACTOR / 2)).setDuration(100);
                        }
                        if(imageView.getWidth() * FACTOR < 2 * screenSize.x) {
                            if (imageView.getX() - (imageView.getWidth() * FACTOR / 2) < (-screenSize.x + centerPoint.x))
                                imageView.animate().x(-screenSize.x + centerPoint.x + (imageView.getWidth() * FACTOR / 2)).setDuration(100);
                            if (imageView.getX() + (imageView.getWidth() * FACTOR / 2) > (screenSize.x + centerPoint.x))
                                imageView.animate().x(screenSize.x + centerPoint.x -(imageView.getWidth() * FACTOR / 2)).setDuration(100);
                        }
                        else {
                            if (imageView.getX() - (imageView.getWidth() * FACTOR / 2) > (-screenSize.x + centerPoint.x))
                                imageView.animate().x(-screenSize.x + centerPoint.x + (imageView.getWidth() * FACTOR / 2)).setDuration(100);
                            if (imageView.getX() + (imageView.getWidth() * FACTOR / 2) < (screenSize.x + centerPoint.x))
                                imageView.animate().x(screenSize.x + centerPoint.x -(imageView.getWidth() * FACTOR / 2)).setDuration(100);
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        centerPoint.set((int)imageView.getX(), (int)(imageView.getY() - 20f));
    }

    protected void scrollImageView(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                point = motionEvent.getPointerId(0);
                currentPosition.set(motionEvent.getRawX(), motionEvent.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                if(point == motionEvent.getPointerId(0)) {
                    offset.set(motionEvent.getRawX() - currentPosition.x, motionEvent.getRawY() - currentPosition.y);
                    if(imageView.getHeight() * FACTOR < 2 * screenSize.y) {
                        if (imageView.getY() - (imageView.getHeight() * FACTOR / 2) < (-screenSize.y + centerPoint.y) && offset.y < 0)
                            offset.y = -scrollingSpeedBeyondBorders;
                        if (imageView.getY() + (imageView.getHeight() * FACTOR / 2) > (screenSize.y + centerPoint.y) && offset.y > 0)
                            offset.y = scrollingSpeedBeyondBorders;
                    }
                    else {
                        if (imageView.getY() - (imageView.getHeight() * FACTOR / 2) > (-screenSize.y + centerPoint.y)  && offset.y > 0)
                            offset.y = scrollingSpeedBeyondBorders;
                        if (imageView.getY() + (imageView.getHeight() * FACTOR / 2) < (screenSize.y + centerPoint.y) && offset.y < 0)
                            offset.y = -scrollingSpeedBeyondBorders;
                    }
                    if(imageView.getWidth() * FACTOR < 2 * screenSize.x) {
                        if (imageView.getX() - (imageView.getWidth() * FACTOR / 2) < (-screenSize.x + centerPoint.x) && offset.x < 0)
                            offset.x = -scrollingSpeedBeyondBorders;
                        if (imageView.getX() + (imageView.getWidth() * FACTOR / 2) > (screenSize.x + centerPoint.x) && offset.x > 0)
                            offset.x = scrollingSpeedBeyondBorders;
                    }
                    else {
                        if (imageView.getX() - (imageView.getWidth() * FACTOR / 2) > (-screenSize.x + centerPoint.x) && offset.x > 0)
                            offset.x = scrollingSpeedBeyondBorders;
                        if (imageView.getX() + (imageView.getWidth() * FACTOR / 2) < (screenSize.x + centerPoint.x) && offset.x < 0)
                            offset.x = -scrollingSpeedBeyondBorders;
                    }
                    imageView.setX(imageView.getX() + offset.x);
                    imageView.setY(imageView.getY() + offset.y);
                }
                else {
                    point = motionEvent.getPointerId(0);
                }
                currentPosition.set(motionEvent.getRawX(), motionEvent.getRawY());
                break;
        }
    }
}