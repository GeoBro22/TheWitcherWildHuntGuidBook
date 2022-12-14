package com.example.thewitcherwildhuntguidbook.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.thewitcherwildhuntguidbook.data.ItemDataSource;
import com.example.thewitcherwildhuntguidbook.data.ItemExtended;

import java.util.ArrayList;

public class ItemsDescriptionViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<ItemExtended>> extendedItemList;
    private final MutableLiveData<Integer> position;
    private final ItemDataSource itemDataSource;

    public ItemsDescriptionViewModel(Application application) {
        super(application);
        System.out.println("view model created");
        itemDataSource = new ItemDataSource(application.getResources());
        position = new MutableLiveData<>(0);
        this.extendedItemList = new MutableLiveData<>(itemDataSource.getItemsWithDescription());
    }

    public MutableLiveData<ArrayList<ItemExtended>> getExtendedItemList() {
        return extendedItemList;
    }

    public MutableLiveData<Integer> getPosition() {
        return position;
    }
}
