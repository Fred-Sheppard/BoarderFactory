package simulation;

// Concrete Decorator for Under Bag
class DutyFreeBag extends PersonDecorator {
    public DutyFreeBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + 2;
    }

}
