package be.dzenali.gamification.entity.archetype;

import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.entity.Monster;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;
import be.dzenali.gamification.item.armor.Gabison;
import be.dzenali.gamification.item.weapon.DualDagger;

import java.util.ArrayList;

public class Rogue extends Player {
    private int energy;

    /**
     * Basic archetype of a "c" user
     */
    public Rogue() {
        super("Rogue",
                1,
                new StatBlock(12,18,12,10,10,12),
                new Gabison(),
                new DualDagger(),
                new ArrayList<Item>());
        this.energy = 100;
    }

    /**
     * Execute a monster if is health is currently lower than 10% of max health
     * @param monster the Monster to execute
     * @return currentHP of monster
     */
    public int execute(Monster monster) {
        if((monster.getHealthPoints().getCurrentHP() < monster.getHealthPoints().getMaxHP()/10) && energy >= 10){
            System.out.println("You execute your enemy!");
            return monster.getHealthPoints().getCurrentHP();
        } else if (energy < 10){
            System.out.println("You don't have enough energy...");
            return 0;
        } else {
            System.out.println("Enemy's life to high.");
            return 0;
        }
    }

    public int shadowClone(){
        return attack()*2;
    }

}
