package ui;

import simulation.Seat;

public class ModernSeatGui implements SeatGui {
    // ANSI color code constants
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window seat color
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle seat color
    private static final String LIGHT_RED = "\u001B[91m";    // Aisle seat color
    private static final String RESET = "\u001B[0m";         // Resets formatting
    
    @Override
    public void paint(Seat seat) {
        // Calculate position with spacing for readability
        int yPosition = seat.row() + 5;
        int xPosition;
        
        // Add a gap in the middle for the aisle
        if (seat.col() < 3) {
            xPosition = seat.col() * 5 + 5;  // so its 5 10 15 out 
        } else {
            xPosition = (seat.col() * 5) + 10;  // so its 20 25 30 out
        }
        
        // Move cursor to position
        System.out.print("\033[" + yPosition + ";" + xPosition + "H");
        
        // Choose color based on column position
        switch(seat.col()) {
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
        System.out.print("S");  // Display Seat
        System.out.print(RESET);
    }
}
