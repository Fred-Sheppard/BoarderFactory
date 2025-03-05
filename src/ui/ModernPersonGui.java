package ui;

import simulation.Passenger;

public class ModernPersonGui implements PersonGui {
    // Instance variable to track if person is seated
    private boolean isSeated = false;
    private int row;
    private int col;
    private Passenger passenger;
    
    // ANSI color code constants
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window area
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle area
    private static final String LIGHT_RED = "\u001B[91m";    // Aisle area
    private static final String RESET = "\u001B[0m";
    
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
        // Choose color based on destination column position
        switch(col) {
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
        
        // Always print P for passengers (whether walking or seated)
        System.out.print("P");
        System.out.print(RESET);
    }
    
    // New method to paint a vertical line of people
    @Override
    public void paintVerticalLine() {
        System.out.println("P");
    }
    
    @Override
    public void setCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public int getRow() {
        return row;
    }
    
    @Override
    public int getCol() {
        return col;
    }
    
    @Override
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
