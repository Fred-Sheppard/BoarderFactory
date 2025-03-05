package ui;

public class LegacyTesting {
    public static void main(String[] args) throws InterruptedException {
        AbstractGuiFactory factory = new LegacyGuiFactory();  // Only difference is using LegacyGuiFactory
        AisleGui aisle = factory.createAisleGui();
        
        // Store all seats in a 2D array for easy access
        SeatGui[][] seats = new SeatGui[6][6];
        
        // Initialize empty plane
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                seats[row][col] = factory.createSeatGui();
                seats[row][col].setCoordinates(row, col);
            }
        }

        // Boarding sequence - each array is {row, col}
        int[][] boardingSequence = {
            {0, 4}, {2, 1}, {5, 5}, {3, 0},
            {1, 3}, {4, 2}, {2, 5}, {5, 1},
            {0, 0}, {3, 4}, {4, 5}, {1, 2},
            {5, 3}, {2, 2}, {4, 0}, {1, 5}
        };
        
        PersonGui person = factory.createPersonGui();
        int waitingCount = boardingSequence.length;
        
        for (int[] boarding : boardingSequence) {
            int targetRow = boarding[0];
            int targetCol = boarding[1];
            person.setCoordinates(targetRow, targetCol); // Set target coordinates for coloring
            
            // Walk person up the aisle
            for (int currentRow = -1; currentRow <= targetRow; currentRow++) {
                drawPlane(seats, aisle, person, currentRow, 2, waitingCount, boardingSequence);
                Thread.sleep(300);
            }
            
            // Move to final seat and sit
            seats[targetRow][targetCol].setOccupied(true);
            
            // Decrease waiting count after passenger is seated
            waitingCount--;
            
            // Show final position with updated waiting line
            drawPlane(seats, aisle, null, -1, -1, waitingCount, boardingSequence);
            Thread.sleep(500);
        }
    }
    
    private static void drawPlane(SeatGui[][] seats, AisleGui aisle, 
                                PersonGui person, int personRow, int personCol, 
                                int waitingCount, int[][] boardingSequence) {
        // Clear console
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("Airplane Boarding Simulation (Legacy Mode)\n");
        
        // Draw waiting line first
        System.out.println("Waiting Line: (" + waitingCount + " passengers)");
        // Draw waiting passengers with their destination indicators
        for (int i = 0; i < waitingCount; i++) {
            if (i == 0 && person != null) {
                // Current passenger (at front of line)
                person.paint();
                System.out.println();
            } else {
                // Create temporary person for each waiting passenger
                PersonGui waitingPerson = new LegacyPersonGui();
                waitingPerson.setCoordinates(boardingSequence[i][0], boardingSequence[i][1]);
                waitingPerson.paint();
                System.out.println();
            }
        }
        System.out.println();
        
        // Draw plane layout
        for (int row = 0; row < 6; row++) {
            // Left side seats
            for (int col = 0; col < 3; col++) {
                seats[row][col].paint();
                System.out.print(" ");
            }
            
            // Print aisle
            System.out.print("    ");
            if (row == personRow && personCol == 2) {
                person.paint();  // Person will be shown with their destination indicator
            } else {
                aisle.paint();
            }
            System.out.print("    ");
            
            // Right side seats
            for (int col = 3; col < 6; col++) {
                seats[row][col].paint();
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("\nLegend:");
        System.out.println("[□]=Window Seat  [○]=Middle Seat  [△]=Aisle Seat  [X]=Occupied");
        System.out.println("Person symbols: □=Going to Window  ○=Going to Middle  △=Going to Aisle");
        System.out.println("║ = Aisle");
    }
} 