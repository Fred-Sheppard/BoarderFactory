package simulation;

// Concrete Decorator for Under Bag
class UnderBag extends PersonDecorator {
    final static private int UNDER_BAG_TIME = 5;
    public UnderBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + UNDER_BAG_TIME;
    }

}
