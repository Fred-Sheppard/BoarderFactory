package ui;

import simulation.Person;

import static ui.TerminalController.*;

public class LegacyPersonGui implements PersonGui {
    private final int xOffset;
    private final int aisleY;

    public LegacyPersonGui(int xOffset, int yOffset, int cols) {
        this.xOffset = xOffset;
        aisleY = cols / 2 + yOffset;
    }

    @Override
    public void paint(Person passenger) {
        int x = passenger.getX() + xOffset;
        moveCursor(x, aisleY);
        String icon = passenger.isStowingBags() ? passenger.getCounter() + "" : "■";
        print(icon);
    }
}
