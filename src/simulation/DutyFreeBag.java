package simulation;

// Concrete Decorator for Duty Free Bag
class DutyFreeBag extends PersonDecorator {
    final static private int DUTY_FREE_BAG = 2;
    public DutyFreeBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + DUTY_FREE_BAG;
    }

    public int getDefaultCounter() {
        return DUTY_FREE_BAG + person.getDefaultCounter();
    }
}
