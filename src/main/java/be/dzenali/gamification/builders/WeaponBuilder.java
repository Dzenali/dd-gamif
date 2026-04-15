package be.dzenali.gamification.builders;

import be.dzenali.gamification.data.WeaponType;
import be.dzenali.gamification.item.Weapon;

public class WeaponBuilder extends EquipmentBuilder {
    private int damage = 18;
    private WeaponType type = WeaponType.HEAVY;


    public WeaponBuilder() {

    }

    public WeaponBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public WeaponBuilder setType(WeaponType type) {
        this.type = type;
        return this;
    }

    public Weapon build() {
        return new Weapon(this.damage, this.type, this.durability, this.value);
    }
}
