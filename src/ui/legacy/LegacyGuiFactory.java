package ui.legacy;

import ui.AbstractGuiFactory;
import ui.AisleGui;
import ui.PersonGui;
import ui.SeatGui;

public class LegacyGuiFactory implements AbstractGuiFactory {
    /**
     * How far from the left the plane should be drawn
     */
    private static final int X_OFFSET = 5;
    /**
     * How far from the top the plane should be drawn
     */
    private static final int Y_OFFSET = 2;

    @Override
    public PersonGui createPersonGui(int cols) {
        return new LegacyPersonGui(X_OFFSET, Y_OFFSET, cols);
    }

    public AisleGui createAisleGui(int rows, int columns) {
        return new LegacyAisleGui(rows, columns, X_OFFSET, Y_OFFSET);
    }

    public SeatGui createSeatGui(int seatsPerColumn) {
        return new LegacySeatGui(X_OFFSET, Y_OFFSET, seatsPerColumn);
    }

}