package be.dzenali.gamification;

import be.dzenali.gamification.entity.Monster;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.entity.archetype.Mage;
import be.dzenali.gamification.entity.archetype.Rogue;
import be.dzenali.gamification.entity.archetype.Warrior;

import java.util.Scanner;

public class CombatSystem {
    private Player player;
    private Monster enemy;
    private boolean playerTurn;
    private CombatLog combatLog;
    private CombatRules combatRules;
    private boolean performDefend;

    public CombatSystem(Player player, Monster enemy) {
        this.player = player;
        this.enemy = enemy;
        this.playerTurn = true;
        this.combatLog = new CombatLog();
        this.combatRules = new CombatRules();
        this.performDefend = false;
    }

    public void startCombat() {
        combatLog.log("=== Combat Started ===");
        combatLog.log(player.getName() + " vs " + enemy.getName());
        combatLog.printLog();

        Scanner scanner = new Scanner(System.in);

        while (player.isAlive() && enemy.isAlive()) {
            if (playerTurn) {
                playerTurn(scanner);
            } else {
                enemyTurn();
            }
            playerTurn = !playerTurn;

            System.out.println("\n" + player + "\n" + enemy + "\n");
        }

        finishCombat();
    }

    private void playerTurn(Scanner scanner) {
        System.out.println("\n--- " + player.getName() + "'s Turn ---");
        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Defend");
        System.out.println("Choose action (1-3):");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                performAttack();
                break;
            case 2:
                performSpell(scanner);
                break;
            case 3:
                performDefend();
                break;
            default:
                System.out.println("Invalid choice! Attacking instead.");
                performAttack();
        }
    }

    private void performAttack() {
        int damage = combatRules.computeDamage(player, enemy);
        combatRules.inflictDamage(enemy, damage);
        combatLog.log(player.getName() + " attacks for " + damage + " damage!");
        System.out.println(player.getName() + " attacks for " + damage + " damage!");
    }

    private void performSpell(Scanner scanner) {
        System.out.println("Available spells:");
        if (player instanceof Warrior) {
            System.out.println("1. Whirlwind Attack (30 rage)");
        } else if (player instanceof Mage) {
            System.out.println("1. Fireball (30 mana)");
            System.out.println("2. Frostbolt (20 mana)");
            System.out.println("3. Heal (15 mana)");
        } else if (player instanceof Rogue) {
            System.out.println("1. Execute (15 energy)");
            System.out.println("2. Shadow Clone (35 energy)");
        }

        System.out.println("Choose spell:");
        String spell = scanner.nextLine();
        int dmg = 0;
        if(player instanceof Warrior){
            dmg = ((Warrior) player).useWhirleWind();
        } else if (player instanceof Mage){
            switch (spell){
                case "1" -> dmg = ((Mage)player).cast("FireBall");
                case "2" -> dmg = ((Mage)player).cast("Frostbolt");
                case "3" -> dmg = ((Mage)player).cast("Heal");
            }
        } else if (player instanceof Rogue){
            switch (spell){
                case "1"-> dmg = ((Rogue) player).execute(enemy);
                case "2"-> dmg = ((Rogue) player).shadowClone();
            }
        }

        if (player instanceof Mage && spell.equalsIgnoreCase("Heal")) {
            combatLog.log(player.getName() + " casts Heal!");
            combatRules.heal(player, dmg);
        } else {
            combatLog.log(player.getName() + " casts " + spell + "!");
            combatRules.inflictDamage(enemy, dmg);
            combatLog.log("Spell hits for " + dmg + " damage!");
            System.out.println("Spell hits for " + dmg + " damage!");
        }
    }

    private void performDefend() {
        this.performDefend = true;
        combatLog.log(player.getName() + " takes a defensive stance!");
        System.out.println(player.getName() + " takes a defensive stance!");
        System.out.println("(Reduced damage on next enemy attack)");
    }

    private void enemyTurn() {
        System.out.println("\n--- " + enemy.getName() + "'s Turn ---");
        int damage = performDefend ? combatRules.computeDamage(enemy, player)/2 :combatRules.computeDamage(enemy, player);
        performDefend = false;
        combatRules.inflictDamage(player, damage);
        combatLog.log(enemy.getName() + " attacks for " + damage + " damage!");
        System.out.println(enemy.getName() + " attacks for " + damage + " damage!");
    }

    private void finishCombat() {
        System.out.println("\n=== Combat Finished ===");
        if (player.isAlive()) {
            System.out.println(player.getName() + " wins!");
            int exp = enemy.getExp();
            player.gainExperience(exp);
            System.out.println("Gained " + exp + " experience points!");
            combatLog.log(player.getName() + " wins! Gained " + exp + " XP!");
        } else {
            System.out.println(enemy.getName() + " wins! " + player.getName() + " is defeated!");
            combatLog.log(player.getName() + " is defeated!");
        }
        combatLog.printLog();
    }

    public CombatLog getCombatLog() {
        return combatLog;
    }
}
