package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thewitcherwildhuntguidbook.R;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentItemsBinding;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {
    FragmentItemsBinding binding;
    ArrayList<Item> items;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemsBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        items = new ArrayList<Item>();
        initArray();
        ItemAdapter itemAdapter = new ItemAdapter(items);

        binding.recyclerView.setAdapter(itemAdapter);
    }

    void initArray() {
        items.add(new Item(R.drawable.silver_sword,"An'ferthe","Magic", "1.81"));
        items.add(new Item(R.drawable.silver_sword,"An'ferthe","Magic", "1.81"));
    }
}