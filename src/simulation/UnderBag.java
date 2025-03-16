package simulation;

// Concrete Decorator for Under Bag
public class UnderBag extends PersonDecorator {
    final static private int UNDER_BAG_TIME = 2;

    public UnderBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + UNDER_BAG_TIME;
    }
}
