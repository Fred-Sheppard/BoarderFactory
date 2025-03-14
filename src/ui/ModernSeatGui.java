package ui;

import simulation.Seat;

public class ModernSeatGui implements SeatGui {
    // ANSI color code constants
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window seat color
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle seat color
    private static final String LIGHT_RED = "\u001B[91m";    // Aisle seat color
    private static final String RESET = "\u001B[0m";         // Resets formatting
    private static final int X_OFFSET = 5;
    private static final int Y_OFFSET = 1;

    private void paint(Seat seat, int seatsPerRow, boolean isOccupied) {
        // Calculate position with spacing for readability
        int xPosition = seat.row() + X_OFFSET;
        int yPosition = seat.col() + Y_OFFSET;

        // Add a gap in the middle for the aisle
        if (seat.col() >= seatsPerRow / 2) {
            yPosition += 1;
        }

        // Move cursor to position
        System.out.print("\033[" + yPosition + ";" + xPosition + "H");

        // Choose color based on column position
        switch (seat.col()) {
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
        System.out.print(isOccupied ? "■" : "□");
        System.out.print(RESET);
    }

    @Override
    public void paintFull(Seat seat, int seatsPerRow) {
        paint(seat, seatsPerRow, true);
    }

    public void paintEmpty(Seat seat, int seatsPerRow) {
        paint(seat, seatsPerRow, false);
    }
}
