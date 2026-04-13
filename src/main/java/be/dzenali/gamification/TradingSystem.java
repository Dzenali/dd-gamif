package be.dzenali.gamification;

import be.dzenali.gamification.data.ArmorType;
import be.dzenali.gamification.data.StatType;
import be.dzenali.gamification.data.WeaponType;
import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Armor;
import be.dzenali.gamification.item.Item;
import be.dzenali.gamification.item.Weapon;
import be.dzenali.gamification.item.consumables.HealthPotion;

import java.util.ArrayList;

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
            int sellValue = (int)(item.getValue()*reputation);
            updateReputation(sellValue);
            return sellValue ;
        } else {
            return 0;
        }
    }

    /**
     * Sell player's armor and return price based on reputation  and equip it with a ragged armor
     * @param player Player
     * @return armor value
     */
    public int sellArmor(Player player){
        int value = (int)(player.getArmor().getValue()*reputation);
        player.setArmor(new Armor(0,ArmorType.LIGHT,0,0));
        updateReputation(value);
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
        int sellValue = (int)((value*reputation*durability)/maxDurability);
        player.setWeapon(new Weapon(1,WeaponType.SHORT,5,0));
        updateReputation(sellValue);
        return sellValue;
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

    /**
     * Try to steal an item from the shop (cannot steal weapons and armors). Requires a dexterity higher than the threshold.
     * @param player Player trying to steal
     * @param item Item to steal
     */
    public void tryToSteal(Player player, Item item){
        int dex = player.getStat(StatType.DEXTERITY);
        int itemValue = item.getValue();
        int itemWeight = item.getWeight();
        if( dex*5 > itemValue*itemWeight ){
            System.out.println("Great success, you stole this item.");
            player.addEquipment(item);
        }
        else {
            updateReputation(itemValue);
            System.out.println("Ho no, you got caught, the merchant dislikes that, you lost some rep...");
        }
    }


    /**
     * Update player's reputation, must stay 0.01 <= reputation <= 2.
     * @param sellValue item value
     */
    private void updateReputation(int sellValue){
        double modificator = (double)sellValue / 10000;
        if(reputation + modificator >= 2){
            reputation = 2;
        } else if (reputation + modificator <= 0) {
            reputation = 0.01;
        } else { reputation += modificator; }
    }

    @Override
    public String toString() {
        return String.format("Staff of malice: " + staff +"\n" +
                "Plain sword: " + sword + "\n" +
                "Buffalo's sweater: " + armor + "\n" +
                "Potion of healing: " + popo);
    }

}
