package ui;

public class ModernPersonGui implements PersonGui {
    // Instance variable to track if person is seated
    private boolean isSeated;
    
    // ANSI color code constants
    private static String DARK_BLUE = "\u001B[34m";    // Color for standing person
    private static String PURPLE = "\u001B[35m";       // Color for seated person
    private static String RESET = "\u001B[0m";         // Resets color back to default
    
    // Constructor - person starts standing (not seated)
    public ModernPersonGui() {
        this.isSeated = false;
    }
    
    // Method to change the person's seated state
    public void setSeatState(boolean seated) {
        this.isSeated = seated;
    }
    
    // Implementation of paint method from PersonGui interface
    @Override
    public void paint() {
        // Choose color based on seated state (ternary operator)
        System.out.print(isSeated ? PURPLE : DARK_BLUE);
        System.out.print("P");                         // Print the person symbol
        System.out.print(RESET);                       // Reset color after printing
    }
    
    // New method to paint a vertical line of people
    @Override
    public void paintVerticalLine() {
        System.out.print(DARK_BLUE);
        System.out.println("P");
        System.out.print(RESET);
    }
}
