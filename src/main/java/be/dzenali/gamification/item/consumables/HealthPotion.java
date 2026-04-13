package be.dzenali.gamification.item.consumables;

import be.dzenali.gamification.entity.Player;
import be.dzenali.gamification.item.Item;

public class HealthPotion extends Item {
    private int healing;

    public HealthPotion() {
        super(50, 1);
        this.healing = 50;
    }

    public void heal(Player player) {
        player.getHealthPoints().updateCurrentHP(healing);
    }

    @Override
    public String toString() {
        return String.format("Health Potion, heals 50hp, value: %d, weight: %d.", getValue(), getWeight());
    }
}
