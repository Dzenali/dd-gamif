package be.dzenali.gamification.entity.monster;

import be.dzenali.gamification.data.Aggressivity;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.entity.Monster;

public class Dragon extends Monster {

    /**
     * The strongest few to be met
     */
    public Dragon() {
        super("Dragon",
                15,
                new StatBlock(20,20,20,20,20,20),
                36,
                20,
                Aggressivity.HIGH,
                5000);
    }

    /**
     * Deals twice less damage once it is grounded
     * @return attack damage
     */
    @Override
    public int getAttackDmg(){
        return canFly() ? super.getAttackDmg() : ((int)(super.getAttackDmg()*0.5));
    }

    /**
     * As 1.5x as much armor once grounded
     * @return armorClass
     */
    @Override
    public int getArmorClass(){
        return canFly() ? super.getArmorClass() : ((int)(super.getArmorClass()*1.5));
    }

    /**
     * Returns whether the dragon is above mid hp and so can fly
     * @return whether it can fly (hp >= max hp/2)
     */
    public boolean canFly(){
        return getHealthPoints().getCurrentHP() >= (getHealthPoints().getMaxHP()/2);
    }
}
