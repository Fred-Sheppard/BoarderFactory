import simulation.*;
import interceptor.Dispatcher;
import interceptor.LoggingInterceptor;

import java.util.Optional;
import java.util.Scanner;

record  UserInput(
        int rows,
        int cols,
        boolean showVisuals,
        boolean forLegacyTerminal,
        Optional<Strategy> strategy) {
};

public class Main {
    public static void main(String[] args) {
        // Set up logging interceptor
        Dispatcher.addInterceptor(new LoggingInterceptor());

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
        System.out.println("\033c");
        System.out.println(results);
    }

    static UserInput collectOptions() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter number of rows on the plane: ");
//        int rows = scanner.nextInt();
//        System.out.print("Enter number of seats in each row: ");
//        int cols = scanner.nextInt();

//        int stratId = scanner.nextInt();
        int rows = 30;
        int cols = 6;
        int stratId = 3;

        Optional<Strategy> strategy = Optional.empty();
        if (stratId < 4) {
            strategy = Optional.of(switch (stratId) {
                case 0 -> new FrontToBackStrategy();
                case 1 -> new BackToFrontStrategy();
                case 2 -> new RandomStrategy();
                case 3 -> new ColumnStrategy();
                default -> throw new IllegalStateException("Unexpected value: " + stratId);
            });
        }
        // TODO
        return new UserInput(rows, cols, true, false, strategy);
    }
}