package be.dzenali.gamification.entity;

import be.dzenali.gamification.data.HealthPoints;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;

import java.util.ArrayList;

public class Player {
    private String name;
    private HealthPoints healthPoints;
    private StatBlock statBlock;
    private Armor armor;
    private Weapon weapon;
    private ArrayList<Item> equipment;
    private int carryingCapacity;
    private int level;
    private int exp;

    public Player(String name,
                  int healtModifier,
                  StatBlock statblock,
                  Armor armor,
                  Weapon weapon,
                  ArrayList<Item> equipment) {
        this.name = name;
        this.healthPoints = new HealthPoints((statblock.get(StatType.CONSTITUTION)*healtModifier), (statblock.get(StatType.CONSTITUTION)*healtModifier));
        this.statBlock = statblock;
        this.armor = armor;
        this.weapon = weapon;
        carryingCapacity = statblock.get(StatType.STRENGTH)*5 + 30;
        setEquipment(equipment, carryingCapacity);
        this.level = 1;
        this.exp = 0;
    }

    public String getName() { return name; }

    public void setName(String name){ this.name = name;}

    public int getArmorClass(){
        return armor.getArmorClass() + armorClassDexBonus();
    }

    public int getStat(StatType statType) { return statBlock.get(statType); }

    public HealthPoints getHealthPoints() { return healthPoints; }

    public Armor getArmor(){ return armor; }

    public Weapon getWeapon(){ return weapon;}

    public int attack(){ return weapon.getDamage();}

    public void addEquipment(Item item){ equipment.add(item); }

    public ArrayList<Item> getEquipment(){ return equipment; }

    public boolean isAlive(){return healthPoints.getCurrentHP() >0;}

    public void removeItem(Item item){
        if(equipment.contains(item)){
            equipment.remove(item);
        } else {
            throw new IllegalArgumentException("Item not in inventory");
        }
    }

    public int getLevel() { return level; }

    public void levelUp(StatType statType) {
        level ++;
        statBlock.increase(statType);
    }

    public void gainExperience(int exp) {this.exp += exp;}

    public int getExperience(){ return exp;}

    public int getResource(){return 0;};

    private void setEquipment(ArrayList<Item> equipment, int maxWeight){
        int weight = 0;
        this.equipment = new ArrayList<>();
        while( weight < maxWeight && !equipment.isEmpty()){
            if(equipment.get(0).getWeight() + weight > maxWeight){
                return;
            } else {
                this.equipment.add(equipment.get(0));
                weight += equipment.get(0).getWeight();
                equipment.remove(0);
            }
        }
    }

    private int armorClassDexBonus(){
        int dexBonus = getStat(StatType.DEXTERITY)/5;
        int armorCapDexBonus = armor.getArmorClassDexBonusCap();
        return Math.min(dexBonus, armorCapDexBonus);
    }
    @Override
    public String toString() {
        return String.format("%s (Lvl %d) - HP: %d/%d, Mana/Rage/energy: %d",
                name, level, healthPoints.getCurrentHP(), healthPoints.getMaxHP(), getResource());
    }
}
