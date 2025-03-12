package simulation;

import java.util.HashMap;

public record SimulationResults(HashMap<Strategy, Integer> results) {
}
