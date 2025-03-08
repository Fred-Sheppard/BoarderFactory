package ui;

import simulation.Passenger;
import simulation.Seat;

public class uiTestingModern {
    public static void main(String[] args) {
        // Create components
        AbstractGuiFactory factory = new ModernGuiFactory();
        PersonGui personGui = factory.createPersonGui();
        SeatGui seatGui = factory.createSeatGui();
        AisleGui aisleGui = factory.createAisleGui();
        
        // Clear screen
        System.out.print("\033[H\033[2J");
        
        // Draw seats and aisle
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 3; col++) {
                seatGui.paint(new Seat(row, col));
            }
            for (int col = 3; col < 6; col++) {
                seatGui.paint(new Seat(row, col));
            }
        }
        aisleGui.paint();
        
        // Create passengers
        Passenger[] passengers = {
            new Passenger("P1", new Seat(2, 0)),
            new Passenger("P2", new Seat(4, 5)),
            new Passenger("P3", new Seat(6, 2)),
            new Passenger("P4", new Seat(8, 4))
        };
        
        // Start passengers in a queue
        for (int i = 0; i < passengers.length; i++) {
            passengers[i].setCurrentPosition(0, i);
        }
        
        // Animation
        for (int step = 0; step < 100; step++) {
            // Clear and redraw
            System.out.print("\033[H\033[2J");
            
            // Draw seats
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 3; col++) {
                    seatGui.paint(new Seat(row, col));
                }
                for (int col = 3; col < 6; col++) {
                    seatGui.paint(new Seat(row, col));
                }
            }
            
            // Draw aisle
            aisleGui.paint();
            
            // Move passengers
            for (int i = 0; i < passengers.length; i++) {
                // Use the passenger's moveTowardDestination method
                passengers[i].moveTowardDestination();
                personGui.paint(passengers[i]);
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
