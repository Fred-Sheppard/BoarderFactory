package simulation;

// Base interface
interface Person {
    int getTime();
    boolean isStowingBags();
    void startStowingBags();
    boolean isSeated();
    void setSeated(boolean seated);
    int getX();
    void setX(int x);
    Seat getSeat();
    int getCounter();
    void decrementCounter();
}
