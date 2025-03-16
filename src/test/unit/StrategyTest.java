package unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import simulation.*;

import java.util.ArrayList;

class StrategyTest {
    private ArrayList<Person> passengers;
    private static final int TEST_ROWS = 4;
    private static final int TEST_COLS = 3;

    @BeforeEach
    void setUp() {
        passengers = new ArrayList<>();
        for (int row = 0; row < TEST_ROWS; row++) {
            for (int col = 0; col < TEST_COLS; col++) {
                passengers.add(new BasicPerson(new Seat(row, col)));
            }
        }
    }

    @Test
    void testFrontToBackStrategy() {
        Strategy strategy = new FrontToBackStrategy();
        strategy.sortPassengers(passengers);
        
        for (int i = 0; i < passengers.size() - 1; i++) {
            Person current = passengers.get(i);
            Person next = passengers.get(i + 1);
            assertTrue(current.getSeat().row() <= next.getSeat().row(),
                    "Passengers should be sorted from front to back");
        }
    }

    @Test
    void testBackToFrontStrategy() {
        Strategy strategy = new BackToFrontStrategy();
        strategy.sortPassengers(passengers);
        
        for (int i = 0; i < passengers.size() - 1; i++) {
            Person current = passengers.get(i);
            Person next = passengers.get(i + 1);
            assertTrue(current.getSeat().row() >= next.getSeat().row(),
                    "Passengers should be sorted from back to front");
        }
    }

    @Test
    void testRandomStrategy() {
        Strategy strategy = new RandomStrategy();
        ArrayList<Person> originalOrder = new ArrayList<>(passengers);
        strategy.sortPassengers(passengers);

        assertFalse(originalOrder.equals(passengers),
                "Random strategy should change the order of passengers");
        
        // Verify all passengers are still present
        assertEquals(originalOrder.size(), passengers.size(),
                "All passengers should still be present after shuffling");
        assertTrue(passengers.containsAll(originalOrder),
                "All original passengers should be present after shuffling");
    }

    @Test
    void testColumnStrategy() {
        Strategy strategy = new ColumnStrategy();
        strategy.sortPassengers(passengers);
       assertTrue(true); // once column is done we will finish this test
    }

    @Test
    void testStrategyWithEmptyList() {
        ArrayList<Person> emptyList = new ArrayList<>();
        
        Strategy[] strategies = {
            new FrontToBackStrategy(),
            new BackToFrontStrategy(),
            new RandomStrategy(),
            new ColumnStrategy()
        };
        
        for (Strategy strategy : strategies) {
            strategy.sortPassengers(emptyList);
            assertEquals(0, emptyList.size(),
                    String.format("%s should handle empty list", strategy.getClass().getSimpleName()));
        }
    }
}
