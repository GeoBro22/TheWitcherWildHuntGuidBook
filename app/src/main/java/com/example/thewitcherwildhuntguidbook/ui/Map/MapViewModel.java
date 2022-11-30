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

    public void setCenterPosition (PointF centerPosition){
        this.centerPosition = new MutableLiveData<>(centerPosition);
    }

    public MutableLiveData<PointF> getCenterPosition(){
        return centerPosition;
    }

    public void setScale(float scale) {
        this.scale = new MutableLiveData<>(scale);
    }

    public MutableLiveData<Float> getScale(){
        return scale;
    }

    public void setPositionX(float positionX) {
        this.positionX = new MutableLiveData<>(positionX);
    }

    public MutableLiveData<Float> getPositionX() {
        return positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = new MutableLiveData<>(positionY);
    }

    public MutableLiveData<Float> getPositionY() {
        return positionY;
    }

    public LiveData<Integer> getMapId(){
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = new MutableLiveData<>(mapId);
    }
}
