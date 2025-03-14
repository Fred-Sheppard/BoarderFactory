import simulation.*;
import ui.AbstractGuiFactory;
import ui.LegacyGuiFactory;
import ui.ModernGuiFactory;

import java.util.*;

public class TuiSimulationBuilder {
    private int rows;
    private int cols;
    private boolean showVisuals;
    private boolean forLegacyTerminal;
    private Strategy strategy;
    private boolean usingAllStrategies;

    public TuiSimulationBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    public TuiSimulationBuilder cols(int cols) {
        this.cols = cols;
        return this;
    }

    public TuiSimulationBuilder showVisuals() {
        this.showVisuals = true;
        return this;
    }

    public TuiSimulationBuilder forLegacyTerminal() {
        this.forLegacyTerminal = true;
        return this;
    }

    public TuiSimulationBuilder useBoardingStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public TuiSimulationBuilder useAllStrategies() {
        this.usingAllStrategies = true;
        return this;
    }

    public Simulation build() {
        if (cols % 2 != 0) {
            throw new IllegalArgumentException("Must have even number of columns");
        }

        if (!showVisuals && forLegacyTerminal) {
            throw new IllegalArgumentException("Cannot specify terminal type with visuals hidden");
        }

        boolean usingOneStrategy = strategy != null;
        if (usingAllStrategies == usingOneStrategy) {
            throw new IllegalArgumentException("One xor all strategies must be used");
        }

        AbstractGuiFactory guiFactory = null;
        if (showVisuals) {
            guiFactory = forLegacyTerminal ? new LegacyGuiFactory() : new ModernGuiFactory();
        }

        List<Strategy> strategies = usingAllStrategies ?
                List.of(
                        new FrontToBackStrategy(),
                        new BackToFrontStrategy(),
                        new RandomStrategy(),
                        new ColumnStrategy()
                )
                : Collections.singletonList(strategy);

        return new Simulation(
                rows,
                cols,
                strategies,
                guiFactory
        );
    }
}
