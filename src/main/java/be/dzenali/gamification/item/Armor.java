package be.dzenali.gamification.item;

import be.dzenali.gamification.data.ArmorType;

public class Armor extends Equipment {
    private final int armorClass;
    private final ArmorType armorType;

    public Armor(int armorClass, ArmorType armorType, int durability, int value) {
        super(durability,value);
        this.armorClass = armorClass;
        this.armorType = armorType;
    }

    public int getArmorClass() {
        return isBroken() ? 10 : armorClass;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    /**
     * Update armor durability based on armor class and damage taken
     * @param damage taken
     */
    public void damageDurability(int damage){
        switch (armorType){
            case LIGHT -> updateDurability((int) (damage*0.5));
            case MEDIUM -> updateDurability((int) (damage*0.8));
            case HEAVY -> updateDurability((int) (damage*1.5));
        }
    }

    /**
     * @return max bonus from dex given armor class
     */
    public int getArmorClassDexBonusCap(){
        int cap = 0;
        switch (armorType){
            case LIGHT -> cap = 5;
            case MEDIUM -> cap =  3;
            case HEAVY -> cap = 0;
        }
        return cap;
    }

    @Override
    public String toString() {
        return String.format("damage: %d, durability: %d, armor type: %s, value: %d",
                getArmorClass(), getDurability(), getArmorType(), getValue());}
}
