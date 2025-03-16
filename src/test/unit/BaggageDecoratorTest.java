package unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import simulation.*;

class BaggageDecoratorTest {
    private Person basePerson;
    private static final int BASE_TIME = 1;
    
    @BeforeEach
    void setUp() {
        basePerson = new BasicPerson(new Seat(1, 0), BASE_TIME);
    }
    
    @Test
    void testDutyFreeBagTime() {
        Person withDutyFree = new DutyFreeBag(basePerson);
        assertTrue(withDutyFree.getTime() > BASE_TIME, "DutyFree bag should add processing time");
        
        withDutyFree.startStowingBags();
        int initialCounter = withDutyFree.getCounter();
        withDutyFree.decrementCounter();
        assertEquals(initialCounter - 1, withDutyFree.getCounter(), "Counter should decrease by 1");
    }
    
    @Test
    void testOverHeadBagTime() {
        Person withOverhead = new OverHeadBag(basePerson);
        assertTrue(withOverhead.getTime() > BASE_TIME, "Overhead bag should add processing time");
        
        withOverhead.startStowingBags();
        int initialCounter = withOverhead.getCounter();
        withOverhead.decrementCounter();
        assertEquals(initialCounter - 1, withOverhead.getCounter(), "Counter should decrease by 1");
    }
    
    @Test
    void testUnderBagTime() {
        Person withUnder = new UnderBag(basePerson);
        assertTrue(withUnder.getTime() > BASE_TIME, "Under seat bag should add processing time");
        
        withUnder.startStowingBags();
        int initialCounter = withUnder.getCounter();
        withUnder.decrementCounter();
        assertEquals(initialCounter - 1, withUnder.getCounter(), "Counter should decrease by 1");
    }
    
    @Test
    void testCombinedBagsTime() {
        Person withAllBags = new DutyFreeBag(new OverHeadBag(new UnderBag(basePerson)));
        int totalTime = withAllBags.getTime();
        
        assertTrue(totalTime > new DutyFreeBag(basePerson).getTime(),
                "Multiple bags should increase total time");
        assertTrue(totalTime > new OverHeadBag(basePerson).getTime(), 
                "Multiple bags should increase total time");
        assertTrue(totalTime > new UnderBag(basePerson).getTime(), 
                "Multiple bags should increase total time");
    }
    
    @Test
    void testStowingStateManagement() {
        Person withBag = new DutyFreeBag(basePerson);
        assertFalse(withBag.isStowingBags(), "Should not be stowing initially");
        
        withBag.startStowingBags();
        assertTrue(withBag.isStowingBags(), "Should be stowing after start");
        assertEquals(withBag.getTime(), withBag.getCounter(), "Counter should be set to total time");
        
        while(withBag.getCounter() > 0) {
            withBag.decrementCounter();
        }
        assertEquals(0, withBag.getCounter(), "Counter should reach zero");
    }
    
    @Test
    void testBagDecoratorIndependence() {
        Person person1 = new DutyFreeBag(new BasicPerson(new Seat(1, 0), BASE_TIME));
        Person person2 = new DutyFreeBag(new BasicPerson(new Seat(2, 0), BASE_TIME));
        
        // Initialize counters by starting stowing
        person1.startStowingBags();
        int person2Time = person2.getTime();
        
        // Verify person2's counter is not affected by person1's stowing
        assertFalse(person2.isStowingBags(), "Second person should not be stowing");
        assertEquals(0, person2.getCounter(), "Second person's counter should not be initialized until they start stowing");
        
        // Start person2 stowing and verify independence
        person2.startStowingBags();
        assertEquals(person2Time, person2.getCounter(), "Counter should be initialized to time when stowing starts");
        
        // Verify changes to person1 don't affect person2
        person1.decrementCounter();
        assertEquals(person2Time, person2.getCounter(), "Counter changes should not affect other persons");
    }
}
