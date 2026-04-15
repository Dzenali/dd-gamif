package be.dzenali.gamification.builders;

import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class PlayerBuilder {
    private String name = "Player";
    private int healthModifier = 8;
    private Weapon weapon = new WeaponBuilder().build();
    private final Map<StatType, Integer> stats = new EnumMap<>(StatType.class);
    private Armor armor = new ArmorBuilder().build();
    private ArrayList<Item> equipment = new ArrayList<Item>();

    public PlayerBuilder() {

    }

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder setHealthModifier(int healthModifier) {
        this.healthModifier = healthModifier;
        return this;
    }

    public PlayerBuilder setWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public PlayerBuilder setStat(StatType type, int value) {
        this.stats.put(type, value);
        return this;
    }

    public PlayerBuilder setArmor(Armor armor) {
        this.armor = armor;
        return this;
    }

    public PlayerBuilder addEquipment(Item item) {
        this.equipment.add(item);
        return this;
    }



    public Player build() {
        StatBlock statBlock = new StatBlock(
                this.stats.getOrDefault(StatType.STRENGTH, 16),
                this.stats.getOrDefault(StatType.DEXTERITY, 14),
                this.stats.getOrDefault(StatType.CONSTITUTION, 15),
                this.stats.getOrDefault(StatType.INTELLIGENCE, 10),
                this.stats.getOrDefault(StatType.WISDOM, 10),
                this.stats.getOrDefault(StatType.CHARISMA, 12)
        );
        return new Player(this.name,
                this.healthModifier,
                statBlock,
                this.armor,
                this.weapon,
                this.equipment);
    }
}
