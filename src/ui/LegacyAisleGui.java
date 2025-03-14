package ui;

public class LegacyAisleGui implements AisleGui {
    private static final int X_OFFSET = 2;
    private static final int AISLE_Y = 3;
    @Override
    public void paint(int rows) {
        // Draw the aisle as a simple vertical line in the center (x=20)
        // Start at row 5 (first seat row) and go through row 14 (last seat row)
        for (int row = 0; row < rows; row++) {
            // Position cursor at the current row, column 20
            System.out.printf("\u001B[%d;%dH", row + X_OFFSET, AISLE_Y);
            
            // Draw the aisle marker with simple character
            System.out.print("=");
        }
    }
}