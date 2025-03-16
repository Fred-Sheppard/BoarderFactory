package unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import simulation.*;

import java.util.ArrayList;
import java.util.List;

class SimulationTest {
    private static final int TEST_ROWS = 3;
    private static final int TEST_COLS = 2;
    private List<Strategy> strategies;
    private Simulation simulation;

    @BeforeEach
    void setUp() {
        strategies = new ArrayList<>();
        strategies.add(new FrontToBackStrategy());
        simulation = new Simulation(TEST_ROWS, TEST_COLS, strategies);
    }

    @Test
    void testSimulationInitialization() {
        assertNotNull(simulation, "Simulation should be created successfully");
        SimulationResults results = simulation.run();
        assertNotNull(results, "Simulation should produce results");
        assertFalse(results.results().isEmpty(), "Results should not be empty");
    }

    @Test
    void testMultipleStrategies() {
        strategies.add(new BackToFrontStrategy());
        strategies.add(new RandomStrategy());
        simulation = new Simulation(TEST_ROWS, TEST_COLS, strategies);
        
        SimulationResults results = simulation.run();
        assertEquals(3, results.results().size(), "Should have results for all strategies");
        
        for (Integer time : results.results().values()) {
            assertTrue(time > 0, "Each strategy should take positive time");
        }
    }

    @Test
    void testPassengerSeating() {
        strategies.clear();
        strategies.add(new FrontToBackStrategy());
        simulation = new Simulation(2, 1, strategies);
        
        SimulationResults results = simulation.run();
        assertTrue(results.results().get(strategies.get(0)) > 0,
                "Simulation should take time to seat passengers");
    }

    @Test
    void testDifferentBoardingSizes() {
        Simulation smallPlane = new Simulation(2, 2, strategies);
        Simulation largePlane = new Simulation(4, 3, strategies);
        
        SimulationResults smallResults = smallPlane.run();
        SimulationResults largeResults = largePlane.run();
        
        assertTrue(largeResults.results().get(strategies.get(0)) >
                  smallResults.results().get(strategies.get(0)),
                "Larger plane should take longer to board");
    }

    @Test
    void testMinimumConfiguration() {
        // Test smallest valid configuration
        Simulation minPlane = new Simulation(1, 1, strategies);
        SimulationResults results = minPlane.run();
        assertTrue(results.results().get(strategies.get(0)) > 0,
                "Should handle minimum configuration");
    }

    @Test
    void testStrategyExecution() {
        // Create a plane with multiple rows but single column for clear front-to-back testing
        simulation = new Simulation(3, 1, strategies);
        SimulationResults results = simulation.run();
        
        // FrontToBack strategy should complete successfully
        assertNotNull(results.results().get(strategies.get(0)),
                "Strategy should complete and record time");
    }
}
