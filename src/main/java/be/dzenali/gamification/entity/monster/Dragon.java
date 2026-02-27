package be.dzenali.gamification.entity.monster;

import be.dzenali.gamification.data.Aggressivity;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.entity.Monster;

public class Dragon extends Monster {
    private boolean canFly = true;

    public Dragon() {
        super("Dragon",
                15,
                new StatBlock(20,20,20,20,20,20),
                35,
                20,
                Aggressivity.HIGH,
                5000);
    }

    @Override
    public int getAttackDmg(){
        canFly = !(getHealthPoints().getCurrentHP() < getHealthPoints().getMaxHP()/2);
        return canFly ? (int)(super.getAttackDmg()*0.5) : (int)(super.getAttackDmg()*1.5);
    }

    @Override
    public int getArmorClass(){
        canFly = !(getHealthPoints().getCurrentHP() < getHealthPoints().getMaxHP()/2);
        return canFly ? (int)(super.getArmorClass()*1.5) : super.getArmorClass();
    }
}
