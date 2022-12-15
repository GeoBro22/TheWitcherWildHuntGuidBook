package com.example.thewitcherwildhuntguidbook.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.thewitcherwildhuntguidbook.data.Item;
import com.example.thewitcherwildhuntguidbook.data.ItemDataSource;
import com.example.thewitcherwildhuntguidbook.data.ItemExtended;

import java.util.ArrayList;

public class ItemsViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<Item>> itemList;
    private final ItemDataSource itemDataSource;

    public ItemsViewModel(Application application) {
        super(application);

        itemDataSource = new ItemDataSource(application.getResources());
        itemList = new MutableLiveData<>(itemDataSource.getItems());
    }

    public MutableLiveData<ArrayList<Item>> getItemList() {
        return itemList;
    }
}
