package ui;

public interface SeatGui {
    void paint();
    void setCoordinates(int row, int col);
    int getRow();
    int getCol();
    void setOccupied(boolean occupied);
    boolean isOccupied();
}
