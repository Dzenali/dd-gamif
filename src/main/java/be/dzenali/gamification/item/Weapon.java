package be.dzenali.gamification.item;

import be.dzenali.gamification.data.WeaponType;

public class Weapon extends Equipment {
    private final int damage;
    private final WeaponType weaponType;


    public Weapon(int damage, WeaponType weaponType, int durability, int value) {
        super(durability,value);
        this.damage = damage;
        this.weaponType = weaponType;
    }

    public int getWeaponDamage() {
        return isBroken() ? 0 : damage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void damageDurability(int damage){
        updateDurability(damage);
    }

    public int getDamage(){
        damageDurability(damage);
        return damage;
    }

    @Override
    public String toString() {
        return String.format("damage: %d, durability: %d, value: %d",
            damage, getDurability(), getValue());}

}
