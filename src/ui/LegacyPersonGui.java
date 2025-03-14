package ui;

import simulation.Person;


public class LegacyPersonGui implements PersonGui {
    // Simple ASCII version without colors
    private static final int AISE_Y_VALUE = 3;

    @Override
    public void paint(Person passenger) {
        // Move cursor to passenger's position with basic offset
        int row = passenger.getX();

        System.out.print("\033[" + row + ";" + AISE_Y_VALUE + "H");

        // Display a simple "P" for passenger
        System.out.print("P");
    }
}