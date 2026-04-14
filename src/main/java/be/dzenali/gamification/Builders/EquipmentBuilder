package be.dzenali.gamification.builders;

import be.dzenali.gamification.item.Equipment;

public class EquipmentBuilder {
    protected int durability = 1200;
    protected int value = 200;

    public EquipmentBuilder() {

    }

    public EquipmentBuilder setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public EquipmentBuilder setValue(int value) {
        this.value = value;
        return this;
    }

    public Equipment build() {
        return new Equipment(this.durability, this.value);
    }
}
