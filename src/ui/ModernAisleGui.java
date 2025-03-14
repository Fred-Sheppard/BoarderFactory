package ui;

public class ModernAisleGui implements AisleGui {
    private static final String AISLE_COLOR = "\u001B[37m"; // White for aisle
    private static final String RESET = "\u001B[0m";

    // TODO different number of seats
    private static final int X_OFFSET = 5;
    private static final int AISLE_Y = 4;

    @Override
    public void paint(int rows) {
        System.out.print("\033c");
        // Draw the aisle as a simple vertical line in the center (x=20)
        // Start at row 5 (first seat row) and go through row 14 (last seat row)
        for (int row = 0; row < rows; row++) {
            // Position cursor at the current row, column 20
            System.out.printf("\u001B[%d;%dH", AISLE_Y, row + X_OFFSET);

            // Draw the aisle marker with simple character
            System.out.print(AISLE_COLOR + "=" + RESET);
        }
    }
}
