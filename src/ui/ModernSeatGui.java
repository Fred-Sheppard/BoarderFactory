package ui;

public class ModernSeatGui implements SeatGui {
    // ANSI color code constants
    private static final String LIGHT_BLUE = "\u001B[94m";   // Window seat color
    private static final String LIGHT_GREEN = "\u001B[92m";  // Middle seat color
    private static final String LIGHT_RED = "\u001B[91m";    // Outside seat color
    private static final String RESET = "\u001B[0m";         // Resets color back to default
    
    private int row;
    private int col;
    private boolean occupied;
    
    public ModernSeatGui() {
        this.occupied = false;
    }
    
    @Override
    public void paint() {
        // Choose color based on column position
        switch(col) {
            case 0:
            case 5:
                System.out.print(LIGHT_BLUE);
                break;
            case 1:
            case 4:
                System.out.print(LIGHT_GREEN);
                break;
            case 2:
            case 3:
                System.out.print(LIGHT_RED);
                break;
        }
        // Print P if occupied, S if empty
        System.out.print(occupied ? "P" : "S");
        System.out.print(RESET);
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
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    @Override
    public boolean isOccupied() {
        return occupied;
    }
}
