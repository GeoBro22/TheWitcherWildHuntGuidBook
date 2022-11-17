package com.example.thewitcherwildhuntguidbook.ui.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.MotionEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thewitcherwildhuntguidbook.R;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentBuildsBinding;

import java.util.Objects;

public class Builds extends Fragment {
    FragmentBuildsBinding binding;

    String[] countries = { "Школа Волка", "Школа Кота", "Школа Медведя", "Школа Мантикоры", "Школа Змеи"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuildsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView selection = (TextView) view.findViewById(R.id.selection);
        //android:text="@string/hello";
        // Инициализируем компонент
        //TextView textView = (TextView) view.findViewById(R.id.textView);
// задаём текст
        //binding.textView.setText("Hello Kitty!");
// или с использованием текстовых ресурсов
        //textView.setText(R.string.hello);

        Spinner spinner = view.findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                if (item.equals("Школа Волка"))
                    selection.setText(item);
                else if (item.equals("Школа Кота"))
                    selection.setText(item);
                else if (item.equals("Школа Медведя"))
                    selection.setText(item);
                else if (item.equals("Школа Мантикоры"))
                    selection.setText(item);
                else if (item.equals("Школа Змеи"))
                    selection.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }
}