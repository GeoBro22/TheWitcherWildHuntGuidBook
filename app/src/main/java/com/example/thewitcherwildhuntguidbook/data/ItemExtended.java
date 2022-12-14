package com.example.thewitcherwildhuntguidbook.data;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemExtended extends Item{
    private String source;
    private String effects;

    public ItemExtended(int weaponResource, String name, String tier, String weight) {
        super(weaponResource, name, tier, weight);
    }

    public ItemExtended(Item item, String source, String effects) {
        this(item.getWeaponResource(), item.getName(), item.getTier(), item.getWeight());
        this.source = source;
        this.effects = effects;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }
}
