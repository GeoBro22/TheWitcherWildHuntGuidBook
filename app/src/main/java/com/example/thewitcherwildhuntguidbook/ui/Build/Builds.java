package com.example.thewitcherwildhuntguidbook.ui.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.MotionEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thewitcherwildhuntguidbook.R;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentBuildsBinding;

import java.util.Objects;

public class Builds extends Fragment {
    FragmentBuildsBinding binding;

    String[] schools = { "Школа Волка", "Школа Кота", "Школа Медведя", "Школа Мантикоры", "Школа Змеи"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuildsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.custom_spinner, schools);
        adapter.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(adapter);
        ConstraintLayout wolf = view.findViewById(R.id.wolf);
        //layout.setVisibility(View.INVISIBLE);
        //view(scrollView);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                if (item.equals("Школа Волка"))
                    wolf.setVisibility(View.VISIBLE);
                else if (item.equals("Школа Кота"))
                    wolf.setVisibility(View.INVISIBLE);
                else if (item.equals("Школа Медведя"))
                    wolf.setVisibility(View.INVISIBLE);
                else if (item.equals("Школа Мантикоры"))
                    wolf.setVisibility(View.INVISIBLE);
                else if (item.equals("Школа Змеи"))
                    wolf.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }
}