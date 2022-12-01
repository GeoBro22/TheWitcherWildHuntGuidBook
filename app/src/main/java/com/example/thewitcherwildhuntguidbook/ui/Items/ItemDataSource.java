package com.example.thewitcherwildhuntguidbook.ui.Items;

import android.content.res.Resources;

import com.example.thewitcherwildhuntguidbook.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemDataSource {
    final private Resources resources;
    private ArrayList<Item> items;

    public ItemDataSource(Resources resources) {
        this.resources = resources;
        initData();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private void initData() {
        items = new ArrayList <> (Arrays.asList(
            new Item(R.drawable.weapon1_resource,
                resources.getString(R.string.weapon1_name),
                resources.getString(R.string.weapon1_tier),
                resources.getString(R.string.weapon1_weight)),
            new Item(R.drawable.weapon2_resource,
                resources.getString(R.string.weapon2_name),
                resources.getString(R.string.weapon2_tier),
                resources.getString(R.string.weapon2_weight)),
            new Item(R.drawable.weapon3_resource,
                resources.getString(R.string.weapon3_name),
                resources.getString(R.string.weapon3_tier),
                resources.getString(R.string.weapon3_weight)),
            new Item(R.drawable.weapon4_resource,
                    resources.getString(R.string.weapon4_name),
                    resources.getString(R.string.weapon4_tier),
                    resources.getString(R.string.weapon4_weight)),
            new Item(R.drawable.weapon5_resource,
                    resources.getString(R.string.weapon5_name),
                    resources.getString(R.string.weapon5_tier),
                    resources.getString(R.string.weapon5_weight)),
            new Item(R.drawable.weapon6_resource,
                    resources.getString(R.string.weapon6_name),
                    resources.getString(R.string.weapon6_tier),
                    resources.getString(R.string.weapon6_weight)),
            new Item(R.drawable.weapon7_resource,
                        resources.getString(R.string.weapon7_name),
                        resources.getString(R.string.weapon7_tier),
                        resources.getString(R.string.weapon7_weight)),
            new Item(R.drawable.weapon8_resource,
                    resources.getString(R.string.weapon8_name),
                    resources.getString(R.string.weapon8_tier),
                    resources.getString(R.string.weapon8_weight)),
            new Item(R.drawable.weapon9_resource,
                    resources.getString(R.string.weapon9_name),
                    resources.getString(R.string.weapon9_tier),
                    resources.getString(R.string.weapon9_weight)),
            new Item(R.drawable.weapon10_resource,
                    resources.getString(R.string.weapon10_name),
                    resources.getString(R.string.weapon10_tier),
                    resources.getString(R.string.weapon10_weight))
        ));
    }
}
