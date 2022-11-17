package com.example.thewitcherwildhuntguidbook.ui.Map;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thewitcherwildhuntguidbook.R;

public class Map extends Fragment {
    MapView mapImage;

    String[] maps = {"Velen", "White Garden", "Skellige", "Kaer Morhen", "Tussent"};
    Spinner spinnerMaps;

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
                Toast.makeText(getContext(),adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                switch (adapterView.getSelectedItem().toString()) {
                    case "Velen":
                        mapImage.setImageResource(R.drawable.map_velen_and_novigrad);
                        break;
                    case "White Garden":
                        mapImage.setImageResource(R.drawable.white_garden);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spinnerMaps.setOnItemSelectedListener(itemSelectedListener);

        mapImage = (MapView) view.findViewById(R.id.image_map_velen);

        view.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getPointerCount() > 1)
                    mapImage.scaleSize(motionEvent);
                else
                    mapImage.scroll(motionEvent);

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    mapImage.scroll(motionEvent);
                    mapImage.scaleSize(motionEvent);
                }
                return true;
            }
        });
    }
}