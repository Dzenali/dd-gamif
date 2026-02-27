package be.dzenali.gamification.entity.monster;

import be.dzenali.gamification.data.Aggressivity;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.entity.Monster;

public class Ork extends Monster {
    private boolean ironWill;

    /**
     * A common enemy
     */
    public Ork(){
        super("Ork",
                3,
                new StatBlock(12,12,14,6,8,5),
                12,
                12,
                Aggressivity.MEDIUM,
                400);
        this.ironWill = false;
    }

    public void ironWill() { ironWill = !ironWill;}

    public boolean ironWillIsActive() { return ironWill; }

    @Override
    public int getArmorClass() {
        return ironWill ? super.getArmorClass() + 6 : super.getArmorClass();
    }

    @Override
    public int getAttackDmg(){
        return ironWill ? super.getAttackDmg() + 3 : super.getAttackDmg();
    }
}
