package ui;

import simulation.Seat;

public class TerminalController {
    public static final String WHITE = "\u001B[37m";   // Window seat color
    public static final String LIGHT_BLUE = "\u001B[94m";   // Window seat color
    public static final String LIGHT_GREEN = "\u001B[92m";  // Middle seat color
    public static final String LIGHT_RED = "\u001B[91m";    // Aisle seat color
    public static final String RESET = "\u001B[0m";

    public static void clearScreen() {
        System.out.print("\033c");
    }

    public static void moveCursor(int x, int y) {
        System.out.printf("\u001B[%d;%dH", y, x);
    }

    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }

    public static void print(String text) {
        System.out.print(RESET + text);
    }

    public static String getColorForSeat(Seat seat, int seatsPerColumn) {
        int colorIndex;
        if (seat.col() < seatsPerColumn / 2) {
            colorIndex = seat.col();
        } else {
            // Reverse the modulo
            colorIndex = seatsPerColumn - (seat.col() % seatsPerColumn) - 1;
        }

        return switch (colorIndex % 3) {
            case 0 -> LIGHT_BLUE;
            case 1 -> LIGHT_GREEN;
            case 2 -> LIGHT_RED;
            default -> throw new IllegalStateException("Unexpected value: " + colorIndex);
        };
    }
}
