/**import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Entity;

public class ChatCS {

    public int calculateInitiative(Entity entity) {
        int dexMod = modifier(entity.getStat().get(StatType.DEXTERITY));
        int reputationBonus = entity.getReputation() / 10;
        return dexMod + reputationBonus;
    }

    public AttackResult resolveAttack(Entity attacker, Entity defender) {

        int attackScore = computeAttackScore(attacker);
        int defenseScore = computeDefense(defender);

        boolean hit = attackScore >= defenseScore;

        if (!hit) {
            return AttackResult.miss();
        }

        int damage = computeDamage(attacker, defender);
        defender.takeDamage(damage);

        return AttackResult.hit(damage);
    }

    private int computeAttackScore(Entity attacker) {
        int base = attacker.getWeapon().calculateAverageDamage(attacker.getStats());
        int reputationBonus = attacker.getReputation() / 5;
        return base + reputationBonus;
    }

    private int computeDefense(Entity defender) {
        return 10 + modifier(defender.getStats().get(StatType.DEXTERITY))
                + defender.getArmor();
    }

    private int computeDamage(Entity attacker, Entity defender) {
        int rawDamage = attacker.getWeapon()
                .calculateAverageDamage(attacker.getStats());

        int reduction = defender.getArmor() / 2;
        return Math.max(0, rawDamage - reduction);
    }

    private int modifier(int stat) {
        return (stat - 10) / 2;
    }
}**/