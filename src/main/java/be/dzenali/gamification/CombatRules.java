package be.dzenali.gamification;

import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Monster;
import be.dzenali.gamification.entity.Player;


/**
 * Class that handles the combat rules of a fight
 */
public class CombatRules {

    /**
     * compute initiative of a player based on is dex and armor type
     * @param player the player
     * @return initiative
     */
    public int calculateInitiative(Player player) {
        int penalty = 0;
        switch (player.getArmor().getArmorType()){
            case MEDIUM -> penalty = 1;
            case HEAVY -> penalty = 2;
        }
        return modifier(player.getStat(StatType.DEXTERITY)) - penalty ;
    }

    /**
     * @param monster the monster
     * @return initiative
     */
    public int calculateInitiative(Monster monster) {
        return modifier(monster.getStat(StatType.DEXTERITY));
    }

    /**
     * compute damage applied to monster
     * @param attacker Player
     * @param defender Monster
     * @return damage to monster from player
     */
    public int computeDamage(Player attacker, Monster defender) {
        int attack = computeAttackScore(attacker);
        int defense = computeDefense(defender);

        return Math.max(attack - defense, 0);
    }

    /**
     * compute damage applied to player
     * @param attacker Monster
     * @param defender Player
     * @return damage to monster from player
     */
    public int computeDamage(Monster attacker, Player defender) {
        int attack = computeAttackScore(attacker);
        int defense = computeDefense(defender);

        return Math.max(attack - defense, 0);
    }

    public void heal(Player player, int heal){ player.getHealthPoints().updateCurrentHP(heal); }

    public void inflictDamage(Player player, int dmg){ player.getHealthPoints().updateCurrentHP(-dmg);}

    public void inflictDamage(Monster monster, int dmg){ monster.getHealthPoints().updateCurrentHP(-dmg);}

    /**
     * Computer player attack damage based on weapon + strength
     * @param attacker
     * @return damage of player
     */
    private int computeAttackScore(Player attacker) {
        return attacker.attack() + attacker.getStat(StatType.STRENGTH) / 4;
    }

    /**
     * Computer monster attack damage based on basic damage and current monster aggressivity
     * @param attacker Monster
     * @return damage of monster
     */
    private int computeAttackScore(Monster attacker) {
        int dmg = 0;
        switch (attacker.getAggressivity()){
            case LOW -> dmg += attacker.getAttackDmg();
            case MEDIUM -> dmg += (attacker.getAttackDmg() + 5);
            case HIGH -> dmg += (attacker.getAttackDmg() + 10);
        }
        return dmg;
    }

    private int computeDefense(Player defender) {
        return defender.getArmorClass();
    }

    /**
     * Compute monster def based on Armor class and Aggressivity
     * @param defender  Monster
     * @return armor value
     */
    private int computeDefense(Monster defender) {
        int bonus = 0;
        switch (defender.getAggressivity()){
            case LOW -> bonus = 5;
            case MEDIUM -> bonus = 0;
            case HIGH -> bonus = -5;
        }
        return defender.getArmorClass() + bonus;
    }

    private int modifier(int stat) {
        return Math.max(0,(stat - 5) / 2);
    }
}
