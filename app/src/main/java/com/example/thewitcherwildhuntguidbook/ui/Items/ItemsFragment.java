package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.example.thewitcherwildhuntguidbook.R;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentItemsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemsFragment extends Fragment {
    FragmentItemsBinding binding;
    ArrayList<Item> itemArrayList;
    ItemsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemArrayList = new ArrayList<>();
        ItemAdapter.ItemClickListener itemClickListener = position -> {

        };

        ItemAdapter itemAdapter = new ItemAdapter(itemArrayList, itemClickListener);


        viewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        viewModel.getItemList().observe(getViewLifecycleOwner(), items -> {
            System.out.println("ItemLIst changed");
            itemArrayList.addAll(items);
        });

        binding.recyclerView.setAdapter(itemAdapter);
    }

}