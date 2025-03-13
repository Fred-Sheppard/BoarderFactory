package ui;

public class LegacyAisleGui implements AisleGui {
    @Override
    public void paint() {
        // Draw the aisle as a simple vertical line in the center (x=20)
        // Start at row 5 (first seat row) and go through row 14 (last seat row)
        for (int row = 5; row <= 14; row++) {
            // Position cursor at the current row, column 20
            System.out.print("\033[" + row + ";20H");
            
            // Draw the aisle marker with simple character
            System.out.print("|");
        }
    }
}