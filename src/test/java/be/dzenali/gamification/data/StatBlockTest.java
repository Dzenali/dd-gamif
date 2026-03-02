package be.dzenali.gamification.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatBlockTest {
    @Test
    public void validateInitUpperBoundTest() {
        StatBlock statBlock = new StatBlock(19,16,10,18,18,18);
        assertEquals(18, statBlock.get(StatType.STRENGTH));
        assertEquals(16, statBlock.get(StatType.DEXTERITY));
        assertEquals(10, statBlock.get(StatType.CONSTITUTION));
        assertEquals(18, statBlock.get(StatType.INTELLIGENCE));
        assertEquals(18, statBlock.get(StatType.WISDOM));
        assertEquals(18, statBlock.get(StatType.CHARISMA));
    }
    @Test
    public void validateInitLowerBoundTest() {
        StatBlock statBlock = new StatBlock(0,-1,10,18,18,18);
        assertEquals(1, statBlock.get(StatType.STRENGTH));
        assertEquals(1, statBlock.get(StatType.DEXTERITY));
        assertEquals(10, statBlock.get(StatType.CONSTITUTION));
        assertEquals(18, statBlock.get(StatType.INTELLIGENCE));
        assertEquals(18, statBlock.get(StatType.WISDOM));
        assertEquals(18, statBlock.get(StatType.CHARISMA));
    }
    @Test
    public void increaseTest() {
        StatBlock statBlock = new StatBlock(12,12,10,18,18,18);
        statBlock.increase(StatType.STRENGTH);
        assertEquals(13, statBlock.get(StatType.STRENGTH));
    }
    @Test
    public void getTest() {
        StatBlock statBlock = new StatBlock(12,12,10,18,18,18);
        int val = statBlock.get(StatType.STRENGTH);
        assertEquals(12, val);
    }

}