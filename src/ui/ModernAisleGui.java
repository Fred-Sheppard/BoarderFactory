package ui;

import static ui.Util.WHITE;

public class ModernAisleGui implements AisleGui {
    private static final String AISLE_COLOR = WHITE;
    private final int xOffset;
    private final int aisleY;
    private final int rows;

    public ModernAisleGui(int rows, int cols, int xOffset, int yOffset) {
        this.rows = rows;
        this.xOffset = xOffset;
        this.aisleY = cols / 2 + yOffset;
    }

    @Override
    public void paint() {
        Util.clearScreen();
        for (int row = 0; row < rows; row++) {
            int x = row + xOffset;
            Util.moveCursor(x, aisleY);
            Util.print(AISLE_COLOR, "=");
        }
    }
}
