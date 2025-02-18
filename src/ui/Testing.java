//used for testing until simulation is finsihed
//need to discuss how paint logic will work with the simulation 
// thinking of changing seat.paint to determine color based on postion of seat (row, col).
//also not sure if we need aisle class for something so simple might be easier print it in simulation
package ui;

public class Testing {
    public static void main(String[] args) {
        // Create a modern GUI factory
        GuiFactory factory = new ModernGuiFactory();
        AisleGui aisle = factory.createAisleGui();  // Create once, reuse
        
       
        // Create 6 rows
        for (int row = 0; row < 6; row++) {
            // Left side seats (Window, Middle, Outside)
            SeatGui windowSeat = factory.createSeatGui(ModernSeatGui.WINDOW);
            SeatGui middleSeat = factory.createSeatGui(ModernSeatGui.MIDDLE);
            SeatGui outsideSeat = factory.createSeatGui(ModernSeatGui.OUTSIDE);
            
            // Print left side
            windowSeat.paint();
            System.out.print(" ");
            middleSeat.paint();
            System.out.print(" ");
            outsideSeat.paint();
            
            // Print aisle
            System.out.print("    ");
            aisle.paint();
            System.out.print("    ");
            
            // Right side seats (Outside, Middle, Window)
            outsideSeat = factory.createSeatGui(ModernSeatGui.OUTSIDE);
            middleSeat = factory.createSeatGui(ModernSeatGui.MIDDLE);
            windowSeat = factory.createSeatGui(ModernSeatGui.WINDOW);
            
            // Print right side
            outsideSeat.paint();
            System.out.print(" ");
            
            // Add a seated person in middle seat of first row
            if (row == 0) {
                PersonGui seatedPerson = factory.createPersonGui();
                ((ModernPersonGui)seatedPerson).setSeatState(true);
                seatedPerson.paint();
            } else {
                middleSeat.paint();
            }
            
            System.out.print(" ");
            windowSeat.paint();
            
            // New row
            System.out.println();
        }
        
        // Print vertical line of people at the end
        System.out.println("\nWaiting Line:");
        PersonGui person = factory.createPersonGui();
        for (int i = 0; i < 4; i++) {
            person.paintVerticalLine();
        }
        
        // Test person reference
        System.out.println("\nPerson Reference:");
        System.out.print("Standing: ");
        person.paint();
        System.out.print("\nSeated: ");
        ((ModernPersonGui)person).setSeatState(true);
        person.paint();
        System.out.println("\n");
    }
} 