package simulation;

// Concrete Decorator for Under Bag
class UnderBag extends PersonDecorator {
    public UnderBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + 5;
    }

}
