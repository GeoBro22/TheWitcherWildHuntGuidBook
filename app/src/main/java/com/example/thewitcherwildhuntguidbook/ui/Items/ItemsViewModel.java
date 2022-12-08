package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ItemsViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Item>> itemList;
    private final ItemDataSource itemDataSource;

    public ItemsViewModel(Application application) {
        super(application);

        itemDataSource = new ItemDataSource(application.getResources());
        itemList = new MutableLiveData<>(itemDataSource.getItems());
        /* ArrayList<Item> items = itemDataSource.getItems();
        for (Item item: items) {
            System.out.println(item.getWeaponResource() + " " +
                    item.getName() + " " + item.getTier() + " " + item.getWeight());
        } */
    }

    public MutableLiveData<ArrayList<Item>> getItemList() {
        return itemList;
    }
}
