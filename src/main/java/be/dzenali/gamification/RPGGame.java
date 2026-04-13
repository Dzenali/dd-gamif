package be.dzenali.gamification;

import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.entity.Monster;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.entity.archetype.Mage;
import be.dzenali.gamification.entity.archetype.Rogue;
import be.dzenali.gamification.entity.archetype.Warrior;
import be.dzenali.gamification.entity.monster.Dragon;
import be.dzenali.gamification.entity.monster.Goblin;
import be.dzenali.gamification.entity.monster.Ork;

import java.util.Scanner;

/**
 * Main entry point for the RPG game.
 */
public class RPGGame {
    private Player player;
    private Scanner scanner;
    private TradingSystem trader;

    public RPGGame() {
        this.scanner = new Scanner(System.in);
        this.trader = new TradingSystem();
    }

    public static void main(String[] args) {
        RPGGame game = new RPGGame();
        game.start();
    }

    public void start() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║          Welcome to the Java RPG Adventure!            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();

        createCharacter();
        mainMenu();
    }

    private void createCharacter() {
        System.out.println("Choose your character class:");
        System.out.println("1. Warrior - High HP and Armor");
        System.out.println("2. Mage - High Intelligence and Mana");
        System.out.println("3. Rogue - High Dexterity and Critical Chance");
        System.out.print("Enter choice (1-3): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your character name: ");
        String name = scanner.nextLine();

        switch(choice) {
            case 1:
                player = new Warrior();
                player.setName(name);
                System.out.println("You chose Warrior class!");
                break;
            case 2:
                player = new Mage();
                player.setName(name);
                System.out.println("You chose Mage class!");
                break;
            case 3:
                player = new Rogue();
                player.setName(name);
                System.out.println("You chose Rogue class!");
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Warrior.");
                player = new Warrior();
                player.setName(name);
        }

        System.out.println(player + "\n");
    }

    private void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("════════════════════════════════════════");
            System.out.println("           MAIN MENU");
            System.out.println("════════════════════════════════════════");
            System.out.println("1. Start Battle");
            System.out.println("2. View Character Stats");
            System.out.println("3. View Equipment Store");
            System.out.println("4. Training (Gain Experience)");
            System.out.println("5. Exit Game");
            System.out.print("Choose option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    startBattle();
                    break;
                case 2:
                    viewCharacterStats();
                    break;
                case 3:
                    viewEquipmentStore();
                    break;
                case 4:
                    training();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thanks for playing! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }

        scanner.close();
    }

    private void startBattle() {
        System.out.println("\n════════════════════════════════════════");
        System.out.println("         ENCOUNTER A MONSTER");
        System.out.println("════════════════════════════════════════");
        System.out.println("Choose difficulty:");
        System.out.println("1. Easy - Goblin");
        System.out.println("2. Medium - Orc");
        System.out.println("3. Hard - Dragon");
        System.out.print("Choose difficulty (1-3): ");

        int difficulty = scanner.nextInt();
        scanner.nextLine();

        Monster enemy = createMonsterByDifficulty(difficulty);
        if (enemy != null) {
            System.out.println("You encounter a " + enemy.getName() + "!");
            CombatSystem combat = new CombatSystem(player, enemy);
            combat.startCombat();
        }
    }

    private Monster createMonsterByDifficulty(int difficulty) {
        switch(difficulty) {
            case 2:
                return new Ork();
            case 3:
                return new Dragon();
            default:
                return new Goblin();
        }
    }

    private void viewCharacterStats() {
        System.out.println("\n════════════════════════════════════════");
        System.out.println("       CHARACTER STATISTICS");
        System.out.println("════════════════════════════════════════");
        System.out.println("Name: " + player.getName());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Experience: " + player.getExperience() + " / " + (100 * player.getLevel()));
        System.out.println();
        System.out.println("Health: " + player.getHealthPoints().getCurrentHP() + " / " + player.getHealthPoints().getMaxHP());
        System.out.println("Mana/rage/energy: " + player.getResource() );
        System.out.println();
        System.out.println("Stats:");
        System.out.println("  Strength: " + player.getStat(StatType.STRENGTH));
        System.out.println("  Intelligence: " + player.getStat(StatType.INTELLIGENCE));
        System.out.println("  Dexterity: " + player.getStat(StatType.DEXTERITY));
        System.out.println("  Armor: " + player.getArmorClass());
        System.out.println();
    }

    private void viewEquipmentStore() {
        System.out.println("\n════════════════════════════════════════");
        //ItemStore.listItems();
        System.out.println(trader.toString());
        System.out.println("════════════════════════════════════════");
    }

    private void training() {
        System.out.println("\n════════════════════════════════════════");
        System.out.println("       TRAINING GROUND");
        System.out.println("════════════════════════════════════════");
        System.out.println("No training, only fighting.");
    }
}

