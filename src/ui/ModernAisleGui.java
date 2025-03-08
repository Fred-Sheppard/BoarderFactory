package ui;

public class ModernAisleGui implements AisleGui {
    private static final String AISLE_COLOR = "\u001B[37m"; // White for aisle
    private static final String RESET = "\u001B[0m";
    
    @Override
    public void paint() {
        // Draw the aisle as a vertical line in the center at x=20
        // Start at row 5 (first seat row) and go through row 14 (last seat row)
        for (int row = 5; row <= 14; row++) {
            // Position cursor at the current row, column 20
            System.out.print("\033[" + row + ";20H");
            
            // Draw the aisle marker
            System.out.print(AISLE_COLOR + "=" + RESET);
        }
    }
}
