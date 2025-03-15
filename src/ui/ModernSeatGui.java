package ui;

import simulation.Seat;

import static ui.TerminalController.*;

public class ModernSeatGui implements SeatGui {
    private final int xOffset;
    private final int yOffset;
    private final int seatsPerColumn;


    public ModernSeatGui(int xOffset, int yOffset, int seatsPerColumn) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.seatsPerColumn = seatsPerColumn;
    }

    private void paint(Seat seat, boolean isOccupied) {
        int x = seat.row() + xOffset;
        int y = seat.col() + yOffset;

        // Add a gap in the middle for the aisle
        int aisleY = seatsPerColumn / 2 + yOffset;
        if (y >= aisleY) {
            y += 1;
        }
        moveCursor(x, y);

        // TODO
        String color = switch (seat.col()) {
            case 0, 5 -> LIGHT_BLUE;
            case 1, 4 -> LIGHT_GREEN;
            case 2, 3 -> LIGHT_RED;
            default -> throw new IllegalStateException("Unexpected value: " + seat.col());
        };
        print(color, isOccupied ? "■" : "□");
    }


    @Override
    public void paintFull(Seat seat, int seatsPerRow) {
        paint(seat, true);
    }

    public void paintEmpty(Seat seat, int seatsPerRow) {
        paint(seat, false);
    }
}
