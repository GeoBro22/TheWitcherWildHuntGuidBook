package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thewitcherwildhuntguidbook.R;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentItemsBinding;

import java.util.ArrayList;

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
        ItemDataSource itemDataSource = new ItemDataSource(getResources());

        itemArrayList = new ArrayList<>(itemDataSource.getItems());
        ItemAdapter itemAdapter = new ItemAdapter(itemArrayList);

        binding.recyclerView.setAdapter(itemAdapter);

        /*viewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        viewModel.getItemList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                itemArrayList = items;

                itemAdapter.notifyDataSetChanged();
                System.out.println(itemAdapter.getItemCount());
                for (Item item: itemArrayList) {
                    System.out.println(item.getWeaponResource() + " " +
                            item.getName() + " " + item.getTier() + " " + item.getWeight());
                }
                System.out.println("sooo");
            }
        });*/

    }
}