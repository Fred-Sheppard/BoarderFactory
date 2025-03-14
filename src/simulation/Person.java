package simulation;

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
    int getDefaultCounter();
    void setCounter(int counter);
    void decrementCounter();
}
