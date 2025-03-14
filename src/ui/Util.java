package ui;

public class Util {
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
}
