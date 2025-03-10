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

}
