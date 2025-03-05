package ui;

public interface PersonGui {
    void paint();  // Abstract method that all person GUIs must implement
    void paintVerticalLine();  // New method
    void setCoordinates(int row, int col);
    int getRow();
    int getCol();
    void setPassenger(simulation.Passenger passenger);  // New method to link with simulation
}
