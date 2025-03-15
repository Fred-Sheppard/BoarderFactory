package ui;

import simulation.Seat;

import static ui.TerminalController.*;

public class LegacySeatGui implements SeatGui {
    private final int xOffset;
    private final int yOffset;
    private final int aisleY;


    public LegacySeatGui(int xOffset, int yOffset, int seatsPerColumn) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.aisleY = seatsPerColumn / 2 + yOffset;
    }

    private void paint(Seat seat, boolean isOccupied) {
        int x = seat.row() + xOffset;
        int y = seat.col() + yOffset;

        // Add a gap in the middle for the aisle
        if (y >= aisleY) {
            y += 1;
        }

        moveCursor(x, y);
        print(isOccupied ? "■" : "□");
    }

    @Override
    public void paintFull(Seat seat, int seatsPerRow) {
        paint(seat, true);
    }

    public void paintEmpty(Seat seat, int seatsPerRow) {
        paint(seat, false);
    }
}
