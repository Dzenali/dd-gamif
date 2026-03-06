package be.dzenali.gamification.data;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HealthPointsTest {
    @Test
    public void initHPTest() {
        HealthPoints hp = new HealthPoints(50,50);
        assertEquals(50, hp.getCurrentHP());
        assertEquals(50, hp.getMaxHP());
    }

    @Test
    public void initCurrentHPLowerThanMaxHPTest() {
        HealthPoints hp = new HealthPoints(60,50);
        assertEquals(50, hp.getCurrentHP());
    }

    @Test
    public void initMaxHPTest() {
        HealthPoints hp = new HealthPoints(50,0);
        assertEquals(1, hp.getMaxHP());
    }

    @Test
    public void getMaxHpTest(){
        HealthPoints hp = new HealthPoints(50,50);
        assertEquals(50, hp.getMaxHP());
    }

    @Test
    public void getCurrentHPTest(){
        HealthPoints hp = new HealthPoints(50,50);
        assertEquals(50, hp.getCurrentHP());
    }
}