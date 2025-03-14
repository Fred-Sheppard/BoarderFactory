package simulation;

// Decorator class
abstract class PersonDecorator implements Person {
    protected Person person;

    public PersonDecorator(Person person) {
        this.person = person;
    }

    @Override
    public int getTime() {
        return person.getTime();
    }

    @Override
    public boolean isStowingBags() {
        return person.isStowingBags();
    }

    @Override
    public void startStowingBags() {
        if (person.getCounter() == 0) {
            person.setCounter(this.getDefaultCounter());
        }
        person.startStowingBags();
    }

    @Override
    public boolean isSeated() {
        return person.isSeated();
    }

    @Override
    public int getX() {
        return person.getX();
    }

    @Override
    public void setX(int x) {
        person.setX(x);
    }

    @Override
    public void setSeated(boolean seated) {
        person.setSeated(seated);
    }

    @Override
    public Seat getSeat() {
        return person.getSeat();
    }

    @Override
    public int getCounter() {
        return person.getCounter();
    }

    @Override
    public void setCounter(int counter) {
        person.setCounter(counter);
    }

    @Override
    public void decrementCounter() {
        person.decrementCounter();
    }
}
