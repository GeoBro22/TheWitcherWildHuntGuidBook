package com.example.thewitcherwildhuntguidbook.ui.Items.Description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thewitcherwildhuntguidbook.data.ItemExtended;
import com.example.thewitcherwildhuntguidbook.databinding.FragmentItemDescriptionBinding;
import com.example.thewitcherwildhuntguidbook.viewmodels.ItemsDescriptionViewModel;

public class ItemDescriptionFragment extends Fragment {
    FragmentItemDescriptionBinding binding;
    ItemsDescriptionViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemDescriptionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        int position = 0;
        Bundle arguments = getArguments();
        if (arguments != null) {
            position = arguments.getInt("ITEM_ID");
        }

        viewModel = new ViewModelProvider(requireActivity()).get(ItemsDescriptionViewModel.class);
        viewModel.getPosition().observe(getViewLifecycleOwner(), pos -> {
            ItemExtended itemExtended;
            if (viewModel.getExtendedItemList().getValue() != null)
                itemExtended = viewModel.getExtendedItemList().getValue().get(pos);
            else
                return;
            binding.weaponName.setText(itemExtended.getName());
            binding.weaponImage.setImageResource(itemExtended.getWeaponResource());
            binding.weaponTier.setText(itemExtended.getTier());
            binding.weaponWeight.setText(itemExtended.getWeight());
            binding.weaponEffects.setText(itemExtended.getEffects());
            binding.weaponSource.setText(itemExtended.getSource());
        });
        viewModel.getPosition().setValue(position);

        binding.backButton.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

    }
}