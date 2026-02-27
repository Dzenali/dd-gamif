package be.dzenali.gamification.entity.archetype;

import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.armor.ChainVest;
import be.dzenali.gamification.item.consumables.HealthPotion;
import be.dzenali.gamification.item.weapon.Espadon;

import java.util.ArrayList;

public class Warrior extends Player {
    private int rage;

    public Warrior() {
        super("Warrior",
                8,
                new StatBlock(16,14,15,10,10,12),
                new ChainVest(),
                new Espadon(),
                new ArrayList<Item>());
        this.addEquipment(new HealthPotion());
    }

    @Override
    public int attack(){
        int dmg = super.attack();
        System.out.println("You attack and gain 10 rage.");
        rage = rage <= 90 ? rage + 10 : rage;
        return dmg;
    }

    public int useWhirleWin(){
        if(rage >= 30){
            rage -= 30;
            System.out.println("Using Whirle Wind!");
            return 3*getStat(StatType.STRENGTH) + attack();
        } else {
            System.out.println("Rage is " + rage + ", it's not enough to throw it... :'(");
            return 0;
        }
    }

    @Override
    public int getResource(){ return rage;}
}
