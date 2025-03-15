package simulation;

// Concrete implementation of Person
class BasicPerson implements Person {
    private static final int DEFAULT_TIME = 1; // Default processing time for a person
    private final int time;
    private boolean stowingBags = false;
    private boolean seated = false;
    private int x;
    private final Seat seat;
    private int counter;

    public BasicPerson(Seat seat, int time) {
        this.x = 0;
        this.time = time;
        this.seat = seat;
    }

    public BasicPerson(Seat seat) {
        this(seat, DEFAULT_TIME);
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public boolean isStowingBags() {
        return stowingBags;
    }

    @Override
    public void startStowingBags() {
        stowingBags = true;
    }

    @Override
    public boolean isSeated() {
        return seated;
    }

    @Override
    public void setSeated(boolean seated) {
        this.seated = seated;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public Seat getSeat() {
        return this.seat;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void decrementCounter() {
        if (counter > 0) {
            counter--;
        }
    }
}
