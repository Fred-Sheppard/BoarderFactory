package ui;

import simulation.Person;

import static ui.Util.*;

public class ModernPersonGui implements PersonGui {
    private final int xOffset;
    private final int aisleY;

    public ModernPersonGui(int xOffset, int yOffset, int cols) {
        this.xOffset = xOffset;
        aisleY = cols / 2 + yOffset;
    }

    @Override
    public void paint(Person passenger) {
        // TODO spacing
        int x = passenger.getX() + xOffset;
        Util.moveCursor(x, aisleY);

        // TODO more than 3 columns
        String color = switch (passenger.getSeat().col()) {
            case 0, 5 -> LIGHT_BLUE;
            case 1, 4 -> LIGHT_GREEN;
            case 2, 3 -> LIGHT_RED;
            default -> throw new IllegalStateException("Unexpected value: " + passenger.getSeat().col());
        };
        String icon = passenger.isStowingBags() ? passenger.getCounter() + "" : "■";
        Util.print(color, icon);
    }
}
