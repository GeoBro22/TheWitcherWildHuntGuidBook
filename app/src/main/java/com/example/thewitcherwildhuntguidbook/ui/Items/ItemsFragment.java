package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thewitcherwildhuntguidbook.R;
import com.example.thewitcherwildhuntguidbook.data.Item;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentItemsBinding;
import com.example.thewitcherwildhuntguidbook.viewmodels.ItemsViewModel;

import java.util.ArrayList;

public class ItemsFragment extends Fragment {
    FragmentItemsBinding binding;
    ArrayList<Item> itemArrayList;
    ItemsViewModel viewModel;

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
            Bundle arguments = new Bundle();
            arguments.putInt("ITEM_ID", position);
            Navigation.findNavController(view).navigate(R.id.action_navigation_items_to_item_description,
                    arguments);
        };

        ItemAdapter itemAdapter = new ItemAdapter(getContext(), itemArrayList, itemClickListener);

        final GridLayoutManager manager = new GridLayoutManager(binding.recyclerView.getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return itemAdapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });

        viewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        viewModel.getItemList().observe(getViewLifecycleOwner(), items -> {
            itemArrayList.addAll(items);
        });

        binding.recyclerView.setAdapter(itemAdapter);
        binding.recyclerView.setLayoutManager(manager);

    }

}