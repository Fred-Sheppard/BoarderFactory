package unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import simulation.*;
import simulation.strategy.BackToFrontStrategy;
import simulation.strategy.FrontToBackStrategy;
import simulation.strategy.RandomStrategy;
import simulation.strategy.Strategy;

import java.util.HashMap;

class SimulationResultsTest {
    private HashMap<Strategy, Integer> testResults;
    private Strategy frontToBack;
    private Strategy backToFront;
    
    @BeforeEach
    void setUp() {
        testResults = new HashMap<>();
        frontToBack = new FrontToBackStrategy();
        backToFront = new BackToFrontStrategy();
    }
    
    @Test
    void testResultsStorage() {
        testResults.put(frontToBack, 100);
        testResults.put(backToFront, 120);
        
        SimulationResults results = new SimulationResults(testResults);
        assertEquals(testResults, results.results(), "Results map should match constructor argument");
    }
    
    @Test
    void testToString() {
        testResults.put(frontToBack, 150); // 2m30s
        SimulationResults results = new SimulationResults(testResults);
        String output = results.toString();
        
        assertTrue(output.contains("FrontToBackStrategy"), "Output should contain strategy name");
        assertTrue(output.contains("150"), "Output should contain ticks value");
        assertTrue(output.contains("2 minutes 30 seconds"), "Output should contain formatted time");
    }
    
    @Test
    void testMultipleStrategies() {
        testResults.put(frontToBack, 100);
        testResults.put(backToFront, 120);
        testResults.put(new RandomStrategy(), 110);
        
        SimulationResults results = new SimulationResults(testResults);
        assertEquals(3, results.results().size(), "Should store results for all strategies");
    }
    
    @Test
    void testEmptyResults() {
        SimulationResults results = new SimulationResults(new HashMap<>());
        assertNotNull(results.toString(), "Should handle empty results without error");
    }
}
