package simulation;

import java.util.List;
import java.util.Optional;

// Note: Simulation shouldn't have any knowledge of the UI being used
// That means no console logging or reading inputs
public class Simulation {
    /**
     * @param rows       The number of rows of seats
     * @param cols       The number of columns in each row
     * @param strategies List of boarding strategies to be executed
     * @param guiFactory If empty, the simulation is run with no visuals
     */
    public Simulation(int rows, int cols, List<Strategy> strategies, Optional<AbstractGuiFactory> guiFactory) {
    }

    public SimulationResults run() {
       return new SimulationResults();
    }
}
