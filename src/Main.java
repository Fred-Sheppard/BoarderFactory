import simulation.*;

import java.util.Optional;
import java.util.Scanner;

record UserInput(
        int rows,
        int cols,
        boolean showVisuals,
        boolean forLegacyTerminal,
        Optional<Strategy> strategy) {
};

public class Main {
    public static void main(String[] args) {
        UserInput input = collectOptions();

        // We could pass the input object to the builder,
        // but that would increase the coupling
        // This way, we can build a simulation without user input
        TuiSimulationBuilder builder = new TuiSimulationBuilder()
                .rows(input.rows())
                .cols(input.cols());

        if (input.showVisuals()) {
            builder.showVisuals();
        }

        if (input.forLegacyTerminal()) {
            builder.forLegacyTerminal();
        }

        if (input.strategy().isPresent()) {
            builder.useBoardingStrategy(input.strategy().get());
        } else {
            builder.useAllStrategies();
        }

        Simulation simulation = builder.build();
        SimulationResults results = simulation.run();
        System.out.println(results);
    }

    static UserInput collectOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows on the plane: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of seats in each row: ");
        int cols = scanner.nextInt();

        int stratId = scanner.nextInt();

        Optional<Strategy> strategy = Optional.empty();
        if (stratId < 3) {
            strategy = Optional.of(switch (stratId) {
                case 0 -> new FrontToBackStrategy();
                case 1 -> new BackToFrontStrategy();
                case 2 -> new RandomStrategy();
                default -> throw new IllegalStateException("Unexpected value: " + stratId);
            });
        }

        return new UserInput(/* ... */);
    }
}