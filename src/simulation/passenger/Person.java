package simulation.passenger;

import simulation.Seat;

// Base interface
public interface Person {
    int getTime();
    boolean isStowingBags();
    void startStowingBags();
    boolean isSeated();
    void setSeated(boolean seated);
    int getX();
    void setX(int x);
    Seat getSeat();
    int getCounter();
    void setCounter(int counter);
    void decrementCounter();
}
