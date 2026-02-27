package be.dzenali.gamification.data;

public class HealthPoints {
    private int currentHP;
    private final int maxHP;

    public HealthPoints(int maxHP, int currentHP) {
        if(maxHP <= 0){ throw new IllegalArgumentException(
                "Health must be > 0. Received: " + maxHP);
        }
        this.currentHP = currentHP;
        this.maxHP = maxHP;
    }

    public int getMaxHP() { return maxHP; }

    public int getCurrentHP() { return currentHP; }

    /**
     * Increase current and max hp.
     * @param hp HealthPoints
     * @return New HealthPoints
     */
    public HealthPoints increasedBy(HealthPoints hp) {
        int tempCurrentHP = this.getCurrentHP() + hp.getCurrentHP();
        int tempMaxHP = this.getMaxHP() + hp.getMaxHP();
        return new HealthPoints(tempCurrentHP, tempMaxHP);
    }

    /**
     * modify current hp by amount
     * @param amount current hp modifier
     * @return current hp value
     */
    public int updateCurrentHP(int amount) {
        if(this.getCurrentHP() + amount > this.getMaxHP()) {
            this.currentHP = this.getMaxHP();
        } else if (this.currentHP + amount <= 0) {
            this.currentHP = 0;
        } else {
            this.currentHP += amount;
        }
        return this.getCurrentHP();
    }
}
