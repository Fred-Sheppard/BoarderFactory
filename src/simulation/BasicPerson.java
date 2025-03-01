package simulation;

// Concrete implementation of Person
class BasicPerson implements Person {
    private int time;

    public BasicPerson(int time) {
        this.time = time;
    } // to maybe add variance to different type of person i.e. elderly
    public BasicPerson() {}
    @Override
    public int getTime() {
        return time;
    }

}
