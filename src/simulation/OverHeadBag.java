package simulation;

// Concrete Decorator for Overhead Bag
class OverHeadBag extends PersonDecorator {
    public OverHeadBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + 10;
    }

}
