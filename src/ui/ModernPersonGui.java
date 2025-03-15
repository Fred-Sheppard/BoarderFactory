package ui;

import simulation.Person;

import static ui.TerminalController.*;

public class ModernPersonGui implements PersonGui {
    private final int xOffset;
    private final int aisleY;
    private final int seatsPerRow;


    public ModernPersonGui(int xOffset, int yOffset, int seatsPerRow) {
        this.xOffset = xOffset;
        aisleY = seatsPerRow / 2 + yOffset;
        this.seatsPerRow = seatsPerRow;
    }

    @Override
    public void paint(Person passenger) {
        int x = passenger.getX() + xOffset;
        TerminalController.moveCursor(x, aisleY);

        // TODO more than 3 columns
        String color = switch (passenger.getSeat().col()) {
            case 0, 5 -> LIGHT_BLUE;
            case 1, 4 -> LIGHT_GREEN;
            case 2, 3 -> LIGHT_RED;
            default -> throw new IllegalStateException("Unexpected value: " + passenger.getSeat().col());
        };
        String icon = passenger.isStowingBags() ? passenger.getCounter() + "" : "■";
        TerminalController.print(color, icon);
    }
}
