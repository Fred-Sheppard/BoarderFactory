package unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import simulation.*;

class PersonDecoratorTest {
    private Person basicPerson;
    private static final int TEST_TIME = 2;

    @BeforeEach
    void setUp() {
        basicPerson = new BasicPerson(new Seat(1, 0), TEST_TIME);
    }

    @Test
    void testDutyFreeBagDecorator() {
        Person personWithDutyFree = new DutyFreeBag(basicPerson);
        
        assertTrue(personWithDutyFree.getTime() > basicPerson.getTime(),
                "DutyFree bag should increase processing time");
        
        personWithDutyFree.startStowingBags();
        assertEquals(personWithDutyFree.getTime(), personWithDutyFree.getCounter(),
                "Counter should be initialized to the total time when stowing starts");
        
        personWithDutyFree.decrementCounter();
        assertEquals(personWithDutyFree.getTime() - 1, personWithDutyFree.getCounter(),
                "Counter should decrease by 1");
    }

    @Test
    void testOverHeadBagDecorator() {
        Person personWithOverhead = new OverHeadBag(basicPerson);
        
        assertTrue(personWithOverhead.getTime() > basicPerson.getTime(),
                "Overhead bag should increase processing time");
        
        personWithOverhead.startStowingBags();
        assertEquals(personWithOverhead.getTime(), personWithOverhead.getCounter(),
                "Counter should be initialized to the total time when stowing starts");
    }

    @Test
    void testUnderBagDecorator() {
        Person personWithUnder = new UnderBag(basicPerson);
        
        assertTrue(personWithUnder.getTime() > basicPerson.getTime(),
                "Under seat bag should increase processing time");
        
        personWithUnder.startStowingBags();
        assertEquals(personWithUnder.getTime(), personWithUnder.getCounter(),
                "Counter should be initialized to the total time when stowing starts");
    }

    @Test
    void testMultipleBagDecorators() {
        Person personWithAllBags = new DutyFreeBag(
            new OverHeadBag(
                new UnderBag(basicPerson)
            )
        );
        
        assertTrue(personWithAllBags.getTime() > new DutyFreeBag(basicPerson).getTime(),
                "Multiple bags should have cumulative time effect");
        
        personWithAllBags.startStowingBags();
        assertTrue(personWithAllBags.isStowingBags(), "Person should be stowing bags");
        assertEquals(personWithAllBags.getTime(), personWithAllBags.getCounter(),
                "Counter should be initialized to total time");
        
        while (personWithAllBags.getCounter() > 0) {
            personWithAllBags.decrementCounter();
        }
        assertEquals(0, personWithAllBags.getCounter(),
                "Counter should reach zero after complete stowing");
    }

    @Test
    void testDecoratorDelegation() {
        Person personWithBag = new DutyFreeBag(basicPerson);
        
        assertEquals(basicPerson.getSeat(), personWithBag.getSeat(),
                "Decorator should delegate seat access");
        
        int newX = 5;
        personWithBag.setX(newX);
        assertEquals(newX, basicPerson.getX(),
                "Decorator should delegate X position updates");
        
        personWithBag.setSeated(true);
        assertTrue(basicPerson.isSeated(),
                "Decorator should delegate seated state");
    }
}
