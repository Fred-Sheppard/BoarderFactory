package simulation;

// Concrete Decorator for Overhead Bag
class OverHeadBag extends PersonDecorator {
    final static private int OVER_HEAD_BAG = 3;
    public OverHeadBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + OVER_HEAD_BAG;
    }

    public int getDefaultCounter() {
        return OVER_HEAD_BAG + person.getDefaultCounter();
    }
}
