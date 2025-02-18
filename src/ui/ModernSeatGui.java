package ui;

public class ModernSeatGui implements SeatGui {
    // ANSI color code constants
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window seat color
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle seat color
    private static final String LIGHT_RED = "\u001B[91m";    // Outside seat color
    private static final String RESET = "\u001B[0m";         // Resets color back to default
    
    // Seat type constants as strings for better usability
    public static final String WINDOW = "window";
    public static final String MIDDLE = "middle";
    public static final String OUTSIDE = "aisle";
    
    private String type;  // Track the type of seat
    
    // Constructor takes a string for seat type
    public ModernSeatGui(String type) {
        this.type = type.toLowerCase();  // Convert to lowercase for case-insensitive comparison
    }
    
    // Implementation of paint method from SeatGui interface
    @Override
    public void paint() {
        // Use the type to determine the color
        switch(type) {
            case WINDOW:
                System.out.print(LIGHT_BLUE);    // Window seats in blue
                break;
            case MIDDLE:
                System.out.print(LIGHT_GREEN);   // Middle seats in green
                break;
            case OUTSIDE:
                System.out.print(LIGHT_RED);     // Outside seats in red
                break;
        }
        System.out.print("S");            // Print the seat symbol
        System.out.print(RESET);          // Reset color back to default
    }
}
