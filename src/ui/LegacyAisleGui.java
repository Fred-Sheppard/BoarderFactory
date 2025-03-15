package ui;

public class LegacyAisleGui implements AisleGui {
    private final int xOffset;
    private final int aisleY;
    private final int rows;

    public LegacyAisleGui(int rows, int cols, int xOffset, int yOffset) {
        this.rows = rows;
        this.xOffset = xOffset;
        this.aisleY = cols / 2 + yOffset;
    }

    @Override
    public void paint() {
        TerminalController.clearScreen();
        for (int row = 0; row < rows; row++) {
            int x = row + xOffset;
            TerminalController.moveCursor(x, aisleY);
            TerminalController.print("=");
        }
    }
}
