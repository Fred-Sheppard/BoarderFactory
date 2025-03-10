package simulation;

// Concrete implementation of Person
class BasicPerson implements Person {
    private static final int DEFAULT_TIME = 5; // Default processing time for a person
    private int time;

    public BasicPerson(int time) {
        this.time = time;
    }

    public BasicPerson() {
        this(DEFAULT_TIME);
    }

    @Override
    public int getTime() {
        return time;
    }
}
