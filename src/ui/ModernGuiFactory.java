package ui;

public class ModernGuiFactory implements AbstractGuiFactory {
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
        return new ModernPersonGui(X_OFFSET, Y_OFFSET, cols);
    }

    public AisleGui createAisleGui(int rows, int columns) {
        return new ModernAisleGui(rows, columns, X_OFFSET, Y_OFFSET);
    }

    public SeatGui createSeatGui(int seatsPerColumn) {
        return new ModernSeatGui(X_OFFSET, Y_OFFSET, seatsPerColumn);
    }
}
