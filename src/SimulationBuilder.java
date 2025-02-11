import simulation.*;

import java.util.*;

public class SimulationBuilder {
    private int rows;
    private int cols;
    private boolean showVisuals;
    private boolean forLegacyTerminal;
    private Strategy strategy;
    private boolean usingAllStrategies;

    public SimulationBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    public SimulationBuilder cols(int cols) {
        this.cols = cols;
        return this;
    }

    public SimulationBuilder showVisuals() {
        this.showVisuals = true;
        return this;
    }

    public SimulationBuilder forLegacyTerminal() {
        this.forLegacyTerminal = true;
        return this;
    }

    public SimulationBuilder useBoardingStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public SimulationBuilder useAllStrategies() {
        this.usingAllStrategies = true;
        return this;
    }

    public Simulation build() {
        if (!showVisuals && forLegacyTerminal) {
            throw new IllegalArgumentException("Cannot specify terminal type with visuals hidden");
        }

        if (usingAllStrategies ^ strategy != null) {
            throw new IllegalArgumentException("Exactly one strategy must be specified");
        }

        Optional<AbstractGuiFactory> guiFactory = Optional.empty();
        if (showVisuals) {
            guiFactory = forLegacyTerminal ? Optional.of(new LegacyGuiFactory()) : Optional.of(new ModernGuiFactory());
        }

        @SuppressWarnings("DataFlowIssue") // Cannot be null, asserted in XOR
        List<Strategy> strategies = usingAllStrategies ?
                Arrays.asList(
                        new FrontToBackStrategy(),
                        new BackToFrontStrategy(),
                        new RandomStrategy()
                )
                : Collections.singletonList(strategy);

        Optional<Strategy> maybeStrategy = usingAllStrategies ? Optional.empty() : Optional.of(strategy);

        return new Simulation(
                rows,
                cols,
                strategies,
                guiFactory
        );
    }
}
