package be.dzenali.gamification.data;

public class HealthPoints {
    private int currentHP;
    private final int maxHP;


    public HealthPoints(int currentHP,int maxHP) {
        if(maxHP <= 0){
            maxHP = 1;
        }
        if(currentHP > maxHP){
            currentHP = maxHP;
        }
        if(currentHP <= 0){
            currentHP = 1;
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
    public int updateCurrentHP(double amount) {
        if(this.getCurrentHP() + amount > this.getMaxHP()) {
            this.currentHP = this.getMaxHP();
        } else if (this.currentHP + amount < 1) {
            this.currentHP = 0;
        } else {
            this.currentHP += (int)amount;
        }
        return this.getCurrentHP();
    }
}
