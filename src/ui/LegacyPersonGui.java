package ui;

import simulation.Passenger;


public class LegacyPersonGui implements PersonGui {
    // Simple ASCII version without colors
    
    @Override
    public void paint(Passenger passenger) {
        // Move cursor to passenger's position with basic offset
        int row = passenger.getCurrentY();
        int col = passenger.getCurrentX() + 20;
        
        System.out.print("\033[" + row + ";" + col + "H");
        
        // Display a simple "P" for passenger
        System.out.print("P");
    }
}