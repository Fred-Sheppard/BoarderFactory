package simulation;

// Concrete Decorator for Under Bag
class UnderBag extends PersonDecorator {
    final static private int UNDER_BAG_TIME = 2;

    public UnderBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + UNDER_BAG_TIME;
    }

    public int getDefaultCounter() {
        return UNDER_BAG_TIME + person.getDefaultCounter();
    }
}
