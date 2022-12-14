package com.example.thewitcherwildhuntguidbook.data;

import android.content.res.Resources;

import com.example.thewitcherwildhuntguidbook.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemDataSource {
    final private Resources resources;
    //private ArrayList<Item> items;

    public ItemDataSource(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<Item> getItems() {
        return initItems();
    }

    public ArrayList<ItemExtended> getItemsWithDescription() {
        ArrayList<Item> items = initItems();
        return initItemsExtended(items);
    }

    private ArrayList<Item> initItems() {
        return new ArrayList <> (Arrays.asList(
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
                        resources.getString(R.string.weapon10_weight)),
                new Item(R.drawable.weapon11_resource,
                        resources.getString(R.string.weapon11_name),
                        resources.getString(R.string.weapon11_tier),
                        resources.getString(R.string.weapon11_weight)),
                new Item(R.drawable.weapon12_resource,
                        resources.getString(R.string.weapon12_name),
                        resources.getString(R.string.weapon12_tier),
                        resources.getString(R.string.weapon12_weight)),
                new Item(R.drawable.weapon13_resource,
                        resources.getString(R.string.weapon13_name),
                        resources.getString(R.string.weapon13_tier),
                        resources.getString(R.string.weapon13_weight))
                ));
    }

    private ArrayList<ItemExtended> initItemsExtended(ArrayList<Item> items) {
        return new ArrayList<>(Arrays.asList(
                new ItemExtended(items.get(0),
                        resources.getString(R.string.weapon1_source),
                        resources.getString(R.string.weapon1_effects)),
                new ItemExtended(items.get(1),
                        resources.getString(R.string.weapon2_source),
                        resources.getString(R.string.weapon2_effects)),
                new ItemExtended(items.get(2),
                        resources.getString(R.string.weapon3_source),
                        resources.getString(R.string.weapon3_effects)),
                new ItemExtended(items.get(3),
                        resources.getString(R.string.weapon4_source),
                        resources.getString(R.string.weapon4_effects)),
                new ItemExtended(items.get(4),
                        resources.getString(R.string.weapon5_source),
                        resources.getString(R.string.weapon5_effects)),
                new ItemExtended(items.get(5),
                        resources.getString(R.string.weapon6_source),
                        resources.getString(R.string.weapon6_effects)),
                new ItemExtended(items.get(6),
                        resources.getString(R.string.weapon7_source),
                        resources.getString(R.string.weapon7_effects)),
                new ItemExtended(items.get(7),
                        resources.getString(R.string.weapon8_source),
                        resources.getString(R.string.weapon8_effects)),
                new ItemExtended(items.get(8),
                        resources.getString(R.string.weapon9_source),
                        resources.getString(R.string.weapon9_effects)),
                new ItemExtended(items.get(9),
                        resources.getString(R.string.weapon10_source),
                        resources.getString(R.string.weapon10_effects)),
                new ItemExtended(items.get(10),
                        resources.getString(R.string.weapon11_source),
                        resources.getString(R.string.weapon11_effects)),
                new ItemExtended(items.get(11),
                        resources.getString(R.string.weapon12_source),
                        resources.getString(R.string.weapon12_effects)),
                new ItemExtended(items.get(12),
                        resources.getString(R.string.weapon13_source),
                        resources.getString(R.string.weapon13_effects))
        ));
    }
}
