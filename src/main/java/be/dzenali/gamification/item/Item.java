package be.dzenali.gamification.item;

public abstract class Item {
    private final int value;
    private final int weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }
    public int getWeight() {
        return weight;
    }
}
