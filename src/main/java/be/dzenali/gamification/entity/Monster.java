package be.dzenali.gamification.entity;

import be.dzenali.gamification.data.Aggressivity;
import be.dzenali.gamification.data.HealthPoints;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.data.StatType;

public class Monster{
    private String name;
    private HealthPoints healthPoints;
    private StatBlock statBlock;
    private final int attack;
    private final int armorClass;
    private Aggressivity aggressivity;
    private int exp;

    public Monster(String name,
                   int level,
                   StatBlock statblock,
                   int attack,
                   int armorClass,
                   Aggressivity aggressivity,
                   int exp) {
        this.name = name;
        this.healthPoints = new HealthPoints((statblock.get(StatType.CONSTITUTION)*level), (statblock.get(StatType.CONSTITUTION)*level));
        this.statBlock = statblock;
        this.attack = attack;
        this.armorClass = armorClass;
        this.aggressivity = aggressivity;
        this.exp = exp;
    }

    public String getName() { return name; }

    public HealthPoints getHealthPoints() { return healthPoints; }

    public int getStat(StatType statType) { return statBlock.get(statType); }

    public StatBlock getStatBlock() { return statBlock; }

    /**
     *  Damages based on attack times aggressivity multiplier
     * @return dmg * mult
     */
    public int getAttackDmg() {
        return (int)(attack *  aggressivity.getMultiplier());
    }
    /**
     *  armor based on attack times aggressivity multiplier
     * @return armor * mult
     */
    public int getArmorClass() {
        return (int)(armorClass /  aggressivity.getMultiplier());
    }

    public Aggressivity getAggressivity() { return aggressivity; }

    public void changeAggressivity(Aggressivity aggressivity) { this.aggressivity = aggressivity; }

    public int getExp() { return exp; }

    public boolean isAlive(){return healthPoints.getCurrentHP() >0;}

    @Override
    public String toString() {
        return String.format("%s - HP: %d/%d",
                name, healthPoints.getCurrentHP(), healthPoints.getMaxHP());
    }
}
