package ui;

import simulation.Passenger;
import simulation.Seat;

public class ModernPersonGui implements PersonGui {
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window area
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle area
    private static final String LIGHT_RED = "\u001B[91m";    // Aisle area
    private static final String RESET = "\u001B[0m";
    
    @Override
    public void paint(Passenger passenger) {
        Seat seat = passenger.getSeat();
        
        // Move cursor to passenger's position with basic offset
        int row = passenger.getCurrentY();
        int col = passenger.getCurrentX() + 20;
        
        System.out.print("\033[" + row + ";" + col + "H");
        
        // Choose color based on seat column
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
        System.out.print("P");
        System.out.print(RESET);
    }
}
