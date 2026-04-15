package be.dzenali.gamification.builders;

import be.dzenali.gamification.data.Aggressivity;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Monster;
import java.util.EnumMap;
import java.util.Map;

public class MonsterBuilder {
    private String name = "Monster";
    private int level = 1;
    private final Map<StatType, Integer> stats = new EnumMap<>(StatType.class);
    private int attack = 5;
    private int armorClass = 5;
    private Aggressivity aggressivity = Aggressivity.MEDIUM;
    private int exp = 50;

    public MonsterBuilder setStat(StatType type, int value) {
        this.stats.put(type, value);
        return this;
    }

    public MonsterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MonsterBuilder setName(int level) {
        this.level = level;
        return this;
    }

    public MonsterBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public MonsterBuilder setArmorClass(int armorClass) {
        this.armorClass = armorClass;
        return this;
    }

    public MonsterBuilder setAggressivity(Aggressivity aggressivity) {
        this.aggressivity = aggressivity;
        return this;
    }

    public MonsterBuilder setExp(int exp) {
        this.exp = exp;
        return this;
    }


    public Monster build() {
        StatBlock statBlock = new StatBlock(
                this.stats.getOrDefault(StatType.STRENGTH, 6),
                this.stats.getOrDefault(StatType.DEXTERITY, 4),
                this.stats.getOrDefault(StatType.CONSTITUTION, 4),
                this.stats.getOrDefault(StatType.INTELLIGENCE, 2),
                this.stats.getOrDefault(StatType.WISDOM, 2),
                this.stats.getOrDefault(StatType.CHARISMA, 1)
        );
        return new Monster(this.name,
                this.level,
                statBlock,
                this.attack,
                this.armorClass,
                this.aggressivity,
                this.exp);
    }
}
