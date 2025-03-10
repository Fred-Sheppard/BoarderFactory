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
}
