package com.example.thewitcherwildhuntguidbook.ui.Map;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thewitcherwildhuntguidbook.R;

public class MapFragment extends Fragment {
    private MapView[] mapViews = new MapView[6];
    private int mapId = 0;
    private TextView scaleText;

    private String[] maps = {"Велен", "Новиград", "Белый Сад", "Скеллиге", "Каэр Морхен"};
    private Spinner spinnerMaps;

    MapViewModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(MapViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(model.getMapId().getValue() != -1) {
            mapViews[mapId].setVisibility(View.INVISIBLE);
            mapId = model.getMapId().getValue();
            spinnerMaps.setSelection(mapId);
            mapViews[mapId].setX(model.getPositionX().getValue());
            mapViews[mapId].setY(model.getPositionY().getValue());
            mapViews[mapId].setScaleX(model.getScale().getValue());
            mapViews[mapId].setScaleY(model.getScale().getValue());
            mapViews[mapId].setFactor(model.getScale().getValue());
            mapViews[mapId].post(() -> mapViews[mapId].setCenterPosition(model.getCenterPosition().getValue()));
            scaleText.setText(Math.round(model.getScale().getValue()*100)+"%");
            mapViews[mapId].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        model.getMapId().setValue(mapId);
        model.getPositionX().setValue(mapViews[mapId].getCurrentX());
        model.getPositionY().setValue(mapViews[mapId].getCurrentY());
        model.getCenterPosition().setValue(mapViews[mapId].getCenterPosition());
        model.getScale().setValue(mapViews[mapId].getFactor());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        model.getPositionX().setValue(mapViews[mapId].getX());
        model.getPositionY().setValue(mapViews[mapId].getY());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapViews[0] = view.findViewById(R.id.map_velen);
        mapViews[0].setMaxScale(14f);
        mapViews[1] = view.findViewById(R.id.map_novigrad);
        mapViews[1].setMaxScale(14f);
        mapViews[2] = view.findViewById(R.id.map_white_orchard);
        mapViews[2].setMaxScale(12f);
        mapViews[3] = view.findViewById(R.id.map_skellige_isles);
        mapViews[3].setMaxScale(14f);
        mapViews[4] = view.findViewById(R.id.map_kaer_morhen);

        spinnerMaps = view.findViewById(R.id.spinner_map);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.custom_spinner, maps);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerMaps.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mapViews[mapId].setVisibility(View.INVISIBLE);
                if (mapId != i) {
                    mapViews[mapId].moveToCenterPosition();
                    mapViews[mapId].setScaleY(1f);
                    mapViews[mapId].setScaleX(1f);
                }
                switch (adapterView.getSelectedItem().toString()) {
                    case "Велен":
                        mapId = 0;
                        break;
                    case "Новиград":
                        mapId = 1;
                        break;
                    case "Белый Сад":
                        mapId = 2;
                        break;
                    case"Скеллиге":
                        mapId = 3;
                        break;
                    case "Каэр Морхен":
                        mapId = 4;
                        break;
                }
                mapViews[mapId].setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spinnerMaps.setOnItemSelectedListener(itemSelectedListener);
        scaleText = view.findViewById(R.id.scaleText);

        ImageButton zoomInButton = view.findViewById(R.id.zoomInButton);
        zoomInButton.setOnClickListener(view1 -> {
            if(!mapViews[mapId].isAnimated()) {
                mapViews[mapId].scaleSize(Math.min(mapViews[mapId].getFactor() * 1.3f, mapViews[mapId].getMaxScale() * 0.8f));
                scaleText.setText(Math.round(mapViews[mapId].getFactor() * 100) + "%");
            }
        });

        ImageButton zoomOutButton = view.findViewById(R.id.zoomOutButton);
        zoomOutButton.setOnClickListener(view12 -> {
            if(!mapViews[mapId].isAnimated()) {
                mapViews[mapId].scaleSize(Math.max(mapViews[mapId].getFactor() / 1.3f, 1f));
                scaleText.setText(Math.round(mapViews[mapId].getFactor() * 100) + "%");
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getPointerCount() > 1) {
                    mapViews[mapId].scaleSize(motionEvent);
                }
                else
                    mapViews[mapId].scroll(motionEvent);

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    mapViews[mapId].scroll(motionEvent);
                    mapViews[mapId].scaleSize(motionEvent);
                }
                scaleText.setText(Math.round(mapViews[mapId].getFactor()*100)+"%");
                return true;
            }
        });
    }
}