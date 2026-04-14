package be.dzenali.gamification.builders;

import be.dzenali.gamification.data.ArmorType;
import be.dzenali.gamification.item.Armor;

public class ArmorBuilder extends EquipmentBuilder {
    private int armorClass = 14;
    private ArmorType type = ArmorType.HEAVY;

    public ArmorBuilder() {

    }

    public ArmorBuilder setArmorClass(int armorClass) {
        this.armorClass = armorClass;
        return this;
    }

    public ArmorBuilder setType(ArmorType type) {
        this.type = type;
        return this;
    }

    public Armor build() {
        return new Armor(this.armorClass, this.type, this.durability, this.value);
    }
}

