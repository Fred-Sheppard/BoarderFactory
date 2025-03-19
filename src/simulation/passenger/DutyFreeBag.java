package simulation.passenger;

// Concrete Decorator for Duty Free Bag
public class DutyFreeBag extends PersonDecorator {
    final static private int DUTY_FREE_BAG = 2;
    public DutyFreeBag(Person person) {
        super(person);
    }

    @Override
    public int getTime() {
        return super.getTime() + DUTY_FREE_BAG;
    }
}
