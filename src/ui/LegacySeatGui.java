package ui;

import simulation.Seat;

public class LegacySeatGui implements SeatGui {
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
        
        // Simple ASCII representation for seats
        System.out.print("[]");  // Box-like seat
    }
}