package be.dzenali.gamification;

import be.dzenali.gamification.data.ArmorType;
import be.dzenali.gamification.data.WeaponType;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;
import be.dzenali.gamification.item.consumables.HealthPotion;

import java.util.HashMap;
import java.util.Map;

public class TradingSystem {
    private Weapon staff = new Weapon(10, WeaponType.LONG, 1000, 500);
    private Weapon sword = new Weapon(15, WeaponType.SHORT, 500, 350);
    private Armor armor = new Armor(13, ArmorType.LIGHT, 850, 400);
    private HealthPotion popo = new HealthPotion();

    public TradingSystem() {
    }



    @Override
    public String toString() {
        return String.format("Staff of malice: " + staff +"\n" +
                "Plain sword: " + sword + "\n" +
                "Buffalo's sweater: " + armor + "\n" +
                "Potion of healing: " + popo);
    }


}
