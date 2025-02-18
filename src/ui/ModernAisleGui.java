package ui;

public class ModernAisleGui implements AisleGui {
    // ANSI color code constant
    private static final String WHITE = "\u001B[37m";  // White color for aisle
    private static final String RESET = "\u001B[0m";   // Reset color
    
    @Override
    public void paint() {
        System.out.print(WHITE);     // Set color to white
        System.out.print("═");       // Print aisle symbol
        System.out.print(RESET);     // Reset color
    }
}
