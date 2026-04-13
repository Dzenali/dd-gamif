package be.dzenali.gamification.data;

public enum Aggressivity {
    LOW(0.5),
    MEDIUM(1.0),
    HIGH(2.0);

    private final double multiplier;

    Aggressivity(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
