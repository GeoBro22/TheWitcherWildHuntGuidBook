package com.example.thewitcherwildhuntguidbook.ui.Map;

import android.graphics.PointF;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {
    private MutableLiveData<Integer> mapId;
    private MutableLiveData<Float> positionX;
    private MutableLiveData<Float> positionY;
    private MutableLiveData<Float> scale;
    private MutableLiveData<PointF> centerPosition;

    public MutableLiveData<PointF> getCenterPosition(){
        if(centerPosition == null)
            centerPosition = new MutableLiveData<>(new PointF(0f,0f));
        return centerPosition;
    }

    public MutableLiveData<Float> getScale(){
        if(scale == null)
            scale = new MutableLiveData<>(1f);
        return scale;
    }

    public MutableLiveData<Float> getPositionX() {
        if(positionX == null)
            positionX = new MutableLiveData<>(0f);
        return positionX;
    }

    public MutableLiveData<Float> getPositionY() {
        if(positionY == null)
            positionY = new MutableLiveData<>(0f);
        return positionY;
    }

    public MutableLiveData<Integer> getMapId(){
        if(mapId == null)
            mapId = new MutableLiveData<>(-1);
        return mapId;
    }
}
