package com.example.thewitcherwildhuntguidbook.ui.Items;

import androidx.annotation.DrawableRes;

public class Item {
    @DrawableRes
    private int weaponResource;
    private String name;
    private String tier;
    private String weight;

    public Item(int weaponResource, String name, String tier, String weight) {
        this.weaponResource = weaponResource;
        this.name = name;
        this.tier = tier;
        this.weight = weight;
    }

    public int getWeaponResource() {
        return weaponResource;
    }

    public void setWeaponResource(int weaponResource) {
        this.weaponResource = weaponResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
