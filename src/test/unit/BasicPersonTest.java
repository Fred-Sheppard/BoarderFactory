package unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import simulation.BasicPerson;
import simulation.Seat;

class BasicPersonTest {
    private BasicPerson person;
    private Seat mockSeat;
    private static final int TEST_TIME = 3;

    @BeforeEach
    void setUp() {
        mockSeat = new Seat(5, 'A');
        person = new BasicPerson(mockSeat, TEST_TIME);
    }

    @Test
    void testInitialization() {
        assertEquals(0, person.getX(), "Initial X position should be 0");
        assertEquals(mockSeat, person.getSeat(), "Seat should match the one provided");
        assertEquals(TEST_TIME, person.getTime(), "Time should match the one provided");
        assertFalse(person.isSeated(), "Person should not be seated initially");
        assertFalse(person.isStowingBags(), "Person should not be stowing bags initially");
    }

    @Test
    void testDefaultConstructor() {
        BasicPerson defaultPerson = new BasicPerson(mockSeat);
        assertEquals(1, defaultPerson.getTime(), "Default time should be 1");
    }

    @Test
    void testMovement() {
        person.setX(3);
        assertEquals(3, person.getX(), "X position should be updated");
    }

    @Test
    void testSeating() {
        person.setSeated(true);
        assertTrue(person.isSeated(), "Person should be seated after setSeated(true)");
    }

    @Test
    void testBagStowing() {
        person.startStowingBags();
        assertTrue(person.isStowingBags(), "Person should be stowing bags after startStowingBags()");
    }

    @Test
    void testCounter() {
        person.setCounter(TEST_TIME);
        assertEquals(TEST_TIME, person.getCounter(), "Counter should be initialized to time value");

        person.decrementCounter();
        assertEquals(TEST_TIME - 1, person.getCounter(), "Counter should decrease by 1");

        for (int i = 0; i < TEST_TIME + 1; i++) {
            person.decrementCounter();
        }
        assertEquals(0, person.getCounter(), "Counter should not go below 0");
    }
}