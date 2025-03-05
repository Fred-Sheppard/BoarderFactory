package ui;

public class LegacySeatGui implements SeatGui {
    private int row;
    private int col;
    private boolean occupied;
    
    public LegacySeatGui() {
        this.occupied = false;
    }
    
    @Override
    public void paint() {
        if (occupied) {
            System.out.print("[X]"); // Occupied seat
        } else {
            switch(col) {
                case 0:
                case 5:
                    System.out.print("[□]"); // Window seat
                    break;
                case 1:
                case 4:
                    System.out.print("[○]"); // Middle seat
                    break;
                case 2:
                case 3:
                    System.out.print("[△]"); // Aisle seat
                    break;
            }
        }
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
