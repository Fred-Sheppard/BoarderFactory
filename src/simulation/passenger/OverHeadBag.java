package simulation.passenger;

// Concrete Decorator for Overhead Bag
public class OverHeadBag extends PersonDecorator {
    final static private int OVER_HEAD_BAG = 3;
    public OverHeadBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + OVER_HEAD_BAG;
    }
}
