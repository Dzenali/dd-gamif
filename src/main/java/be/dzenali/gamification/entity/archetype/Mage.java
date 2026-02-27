package be.dzenali.gamification.entity.archetype;

import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;
import be.dzenali.gamification.item.armor.Pajama;
import be.dzenali.gamification.item.weapon.Toothpick;

import java.util.ArrayList;

public class Mage extends Player {
    private int mana;

    public Mage() {
        super("Mage",
                3,
                new StatBlock(8,8,10,18,14,12),
                new Pajama(),
                new Toothpick(),
                new ArrayList<Item>());
        this.mana = 300;
    }

    public int cast(String spellName){
        switch (spellName){
            case "FireBall" -> {
                if(mana >= 30){
                    mana -= 30;
                    System.out.println("Casting Fire Ball!");
                    return 65;
                }
            }
            case "IceSpear" -> {
                if(mana >= 20) {
                    mana -= 20;
                    System.out.println("Casting Ice Spear!");
                    return 50;
                }
            }
            case "Heal" -> {
                if(mana >= 15){
                    mana -= 15;
                    System.out.println("Casting Heal!");
                    return 15;
                }
            }
        }
        return 0;
    }

    @Override
    public int getResource(){ return mana;}
}
