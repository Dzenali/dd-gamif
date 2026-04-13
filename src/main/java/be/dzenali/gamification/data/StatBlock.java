package be.dzenali.gamification.data;

import java.util.EnumMap;
import java.util.Map;

/**
 * Data type of entity stateblock
 */
public class StatBlock {

    private final Map<StatType, Integer> stats = new EnumMap<>(StatType.class);

    public StatBlock(int str, int dex, int con, int intel, int wis, int cha) {
        stats.put(StatType.STRENGTH, validateInit(str));
        stats.put(StatType.DEXTERITY, validateInit(dex));
        stats.put(StatType.CONSTITUTION, validateInit(con));
        stats.put(StatType.INTELLIGENCE, validateInit(intel));
        stats.put(StatType.WISDOM, validateInit(wis));
        stats.put(StatType.CHARISMA, validateInit(cha));
    }

    public int get(StatType type) {
        return stats.get(type);
    }

    public void increase(StatType type) {
        if(validateIncrease(type)) { stats.put(type, stats.get(type) + 1); }
        else{
            System.out.println("Stat" + type + "cannot increase above 20.");
        }
    }

    private int validateInit(int value) {
        if (value < 1) {
            value = 1;
        } else if (value > 18) {
            value = 18;
        }
        return value;
    }

    private boolean validateIncrease(StatType stat) {
        return stats.get(stat) < 20;
    }

}