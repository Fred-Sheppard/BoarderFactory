package simulation;

import simulation.strategy.Strategy;

import java.util.HashMap;
import java.util.Map;

public record SimulationResults(HashMap<Strategy, Integer> results) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n=== Boarding Simulation Results ===\n\n");
        
        Map.Entry<Strategy, Integer> bestStrategy = null;
        for (Map.Entry<Strategy, Integer> entry : results.entrySet()) {
            if (bestStrategy == null || entry.getValue() < bestStrategy.getValue()) {
                bestStrategy = entry;
            }
        }
        
        for (Map.Entry<Strategy, Integer> entry : results.entrySet()) {
            Strategy strategy = entry.getKey();
            int ticks = entry.getValue();
            
            sb.append(String.format("Strategy: %s\n", strategy.getClass().getSimpleName()));
            sb.append(String.format("Time taken: %d ticks\n", ticks));
            
            int minutes = ticks / 60;
            int seconds = ticks % 60;
            sb.append(String.format("Real-time estimate: %d minutes %d seconds\n", minutes, seconds));
            
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
