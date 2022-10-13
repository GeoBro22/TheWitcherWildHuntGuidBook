package com.example.thewitcherwildhuntguidbook.ui.Items;

public class Item {
    private int weaponResourceOne;
    private String nameOne;
    private String tierOne;
    private String weightOne;

    private int weaponResourceTwo;
    private String nameTwo;
    private String tierTwo;
    private String weightTwo;

    public Item(int weaponResourceOne, String nameOne, String tierOne, String weightOne,
                int weaponResourceTwo, String nameTwo, String tierTwo, String weightTwo) {
        this.weaponResourceOne = weaponResourceOne;
        this.nameOne = nameOne;
        this.tierOne = tierOne;
        this.weightOne = weightOne;
        this.weaponResourceTwo = weaponResourceTwo;
        this.nameTwo = nameTwo;
        this.tierTwo = tierTwo;
        this.weightTwo = weightTwo;
    }

    public int getWeaponResourceOne() {
        return weaponResourceOne;
    }

    public void setWeaponResourceOne(int weaponResourceOne) {
        this.weaponResourceOne = weaponResourceOne;
    }

    public String getNameOne() {
        return nameOne;
    }

    public void setNameOne(String nameOne) {
        this.nameOne = nameOne;
    }

    public String getTierOne() {
        return tierOne;
    }

    public void setTierOne(String tierOne) {
        this.tierOne = tierOne;
    }

    public String getWeightOne() {
        return weightOne;
    }

    public void setWeightOne(String weightOne) {
        this.weightOne = weightOne;
    }

    public int getWeaponResourceTwo() {
        return weaponResourceTwo;
    }

    public void setWeaponResourceTwo(int weaponResourceTwo) {
        this.weaponResourceTwo = weaponResourceTwo;
    }

    public String getNameTwo() {
        return nameTwo;
    }

    public void setNameTwo(String nameTwo) {
        this.nameTwo = nameTwo;
    }

    public String getTierTwo() {
        return tierTwo;
    }

    public void setTierTwo(String tierTwo) {
        this.tierTwo = tierTwo;
    }

    public String getWeightTwo() {
        return weightTwo;
    }

    public void setWeightTwo(String weightTwo) {
        this.weightTwo = weightTwo;
    }
}
