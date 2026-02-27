package be.dzenali.gamification.item;

import be.dzenali.gamification.data.ArmorType;

public class Equipment {
    private int durability;
    private int maxDurability;
    private int value;

    public Equipment(int durability, int value) {
        this.value = value;
        this.durability = durability;
        this.maxDurability = durability;
    }

    public boolean isBroken(){
        return durability <= 0;
    }

    public int getDurability() {
        return isBroken() ? 0 : durability;
    }

    public int getValue() {
        return value;
    }

    public void updateDurability(int durability){
        this.durability += durability;
    }

    public int getMaxDurability() { return maxDurability; }
}
