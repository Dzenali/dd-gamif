package be.dzenali.gamification;

import be.dzenali.gamification.data.ArmorType;
import be.dzenali.gamification.data.WeaponType;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Equipment;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;
import be.dzenali.gamification.item.consumables.HealthPotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles trading system
 */
public class TradingSystem {
    private double reputation;
    private Weapon staff = new Weapon(10, WeaponType.LONG, 1000, 500);
    private Weapon sword = new Weapon(15, WeaponType.SHORT, 500, 350);
    private Armor armor = new Armor(13, ArmorType.LIGHT, 850, 400);
    private HealthPotion popo = new HealthPotion();

    public TradingSystem() {
        this.reputation = 0.1;
    }

    /**
     * Sell an item from player inventory based on it's worldly reputation
     * @param playerItems ArrayList of player's items
     * @param item item to sell
     * @return value of sold item
     */
    public int sellItem(ArrayList<Item> playerItems, Item item){
        if(playerItems.contains(item)){
            playerItems.remove(item);
            return (int)(item.getValue()*reputation);
        } else {
            return 0;
        }
    }

    public void updateReputation(int modif){
        reputation += modif;
    }

    /**
     * Sell player's armor and return price based on reputation  and equip it with a ragged armor
     * @param player Player
     * @return armor value
     */
    public int sellArmor(Player player){
        int value = (int)(player.getArmor().getValue()*reputation);
        player.setArmor(new Armor(0,ArmorType.LIGHT,0,0));
        return value;
    }
    /**
     * Sell player's weapon and return price based on reputation and armor's state and equip it with a weapon equivalent to its fists
     * @param player Player
     * @return armor value
     */
    public int sellWeapon(Player player){
        int value = player.getWeapon().getValue();
        int durability = player.getWeapon().getDurability();
        int maxDurability = player.getWeapon().getMaxDurability();
        player.setWeapon(new Weapon(1,WeaponType.SHORT,5,0));
        return (int)((value*reputation*durability)/maxDurability);
    }

    /**
     * Buy an item (currently not checking if it is in the shop) with price based on reputation + item value
     * @param player Player
     * @param playerGold Amount of gold owned by the player
     * @param item Item to buy
     */
    public void buy(Player player, int playerGold, Item item){
        int itemBuyPrice = (int)(item.getValue()/reputation);
        if(itemBuyPrice > playerGold){
            System.out.println("You don't have enough gold to buy this item");
        } else {
            player.addEquipment(item);
            System.out.println("You buy " + item);
        }
    }

    @Override
    public String toString() {
        return String.format("Staff of malice: " + staff +"\n" +
                "Plain sword: " + sword + "\n" +
                "Buffalo's sweater: " + armor + "\n" +
                "Potion of healing: " + popo);
    }

}
