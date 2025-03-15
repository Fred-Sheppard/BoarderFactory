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

        String color = getColorForSeat(passenger.getSeat(), seatsPerRow);
        String icon = passenger.isStowingBags() ? passenger.getCounter() + "" : "■";
        TerminalController.print(color, icon);
    }
}
