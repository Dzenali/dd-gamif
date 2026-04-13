package be.dzenali.gamification.entity.monster;

import be.dzenali.gamification.data.Aggressivity;
import be.dzenali.gamification.data.StatBlock;
import be.dzenali.gamification.entity.Monster;

public class Goblin extends Monster {

    /**
     * LaughingStock / Punchingbag
     */
    public Goblin() {
        super("Goblin",
                1,
                new StatBlock(6,4,4,2,2,1),
                5,
                5,
                Aggressivity.MEDIUM,
                50);
    }
}
