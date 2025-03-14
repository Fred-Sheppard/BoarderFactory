package ui;

import simulation.Person;
import simulation.Seat;

public class ModernPersonGui implements PersonGui {
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window area
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle area
    private static final String LIGHT_RED = "\u001B[91m";    // Aisle area
    private static final String RESET = "\u001B[0m";
    private static final int X_OFFSET = 5;
    private static final int AISE_Y_VALUE = 4;

    @Override
    public void paint(Person passenger) {
        // TODO spacing
        // TODO boxes
        // TODO display person when they're seated
        Seat seat = passenger.getSeat();

        // Move cursor to passenger's position with basic offset
        int row = passenger.getX() + X_OFFSET;

        System.out.print("\033[" + AISE_Y_VALUE + ";" + row + "H");

        // Choose color based on seat column
        switch (seat.col()) {
            case 0:
            case 5:
                System.out.print(LIGHT_BLUE);
                break;
            case 1:
            case 4:
                System.out.print(LIGHT_GREEN);
                break;
            case 2:
            case 3:
                System.out.print(LIGHT_RED);
                break;
        }
        String icon = passenger.getCounter() == 0 ? "■" : passenger.getCounter() + "";
        System.out.print(icon);
        System.out.print(RESET);
    }
}
