import simulation.*;
import interceptor.Dispatcher;
import interceptor.LoggingInterceptor;
import simulation.strategy.*;
import ui.TerminalController;

import java.util.Optional;
import java.util.Scanner;

record UserInput(
        int rows,
        int cols,
        boolean showVisuals,
        boolean forLegacyTerminal,
        Optional<Strategy> strategy) {
}

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
        TerminalController.clearScreen();
        System.out.println(results.toString());
    }

    static UserInput collectOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows on the plane: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of seats in each row: ");
        int cols = scanner.nextInt();

        Strategy[] strategies = {new FrontToBackStrategy(), new BackToFrontStrategy(), new RandomStrategy(), new ColumnStrategy()};
        System.out.println("Select a strategy:");
        int i = 0;
        while (i < strategies.length) {
            System.out.printf("%d. %s%n", i, strategies[i]);
            i++;
        }
        System.out.printf("%d. All%n", i);
        int stratId = scanner.nextInt();
        Optional<Strategy> strategy = Optional.empty();
        if (stratId < 4) {
            strategy = Optional.of(strategies[stratId]);
        }
        // Otherwise, run all strategies

        System.out.print("Should a GUI be used? Y/N: ");
        boolean showVisuals = scanner.next().equalsIgnoreCase("y");

        boolean forLegacyTerminal = false;
        if (showVisuals) {
            System.out.print("Does your terminal support colour? Y/N: ");
            forLegacyTerminal = !scanner.next().equalsIgnoreCase("y");
        }

        return new UserInput(rows, cols, showVisuals, forLegacyTerminal, strategy);
    }
}