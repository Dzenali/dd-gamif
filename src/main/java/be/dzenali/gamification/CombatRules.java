package be.dzenali.gamification;

import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Monster;
import be.dzenali.gamification.entity.Player;

public class CombatRules {

    public int calculateInitiative(Player player) {
        int penalty = 0;
        switch (player.getArmor().getArmorType()){
            case MEDIUM -> penalty = 1;
            case HEAVY -> penalty = 2;
        }
        return modifier(player.getStat(StatType.DEXTERITY)) - penalty ;
    }

    public int calculateInitiative(Monster monster) {
        return modifier(monster.getStat(StatType.DEXTERITY));
    }

    public int computeDamage(Player attacker, Monster defender) {
        int attack = computeAttackScore(attacker);
        int defense = computeDefense(defender);

        return Math.max(attack - defense, 0);
    }

    public int computeDamage(Monster attacker, Player defender) {
        int attack = computeAttackScore(attacker);
        int defense = computeDefense(defender);

        return Math.max(attack - defense, 0);
    }

    public void heal(Player player, int heal){ player.getHealthPoints().updateCurrentHP(heal); }

    public void inflictDamage(Player player, int dmg){ player.getHealthPoints().updateCurrentHP(-dmg);}

    public void inflictDamage(Monster monster, int dmg){ monster.getHealthPoints().updateCurrentHP(-dmg);}

    private int computeAttackScore(Player attacker) {
        return attacker.attack() + attacker.getStat(StatType.STRENGTH) / 4;
    }

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
