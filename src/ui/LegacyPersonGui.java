package ui;

import simulation.Passenger;

public class LegacyPersonGui implements PersonGui {
    private boolean isSeated = false;
    private int row;
    private int col;
    private Passenger passenger;
    
    @Override
    public void paint() {
        // Use different ASCII characters based on destination column
        switch(col) {
            case 0:
            case 5:
                System.out.print("□"); // Going to window seat
                break;
            case 1:
            case 4:
                System.out.print("○"); // Going to middle seat
                break;
            case 2:
            case 3:
                System.out.print("△"); // Going to aisle seat
                break;
        }
    }
    
    @Override
    public void paintVerticalLine() {
        System.out.println("P");
    }
    
    public void setSeatState(boolean seated) {
        this.isSeated = seated;
    }
    
    @Override
    public void setCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public int getRow() {
        return row;
    }
    
    @Override
    public int getCol() {
        return col;
    }
    
    @Override
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}