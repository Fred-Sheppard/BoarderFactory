package simulation;

import java.util.ArrayList;
import java.util.Comparator;

public class FrontToBackStrategy implements Strategy {
    @Override
    public void sortPassengers(ArrayList<Person> passengers) {
        passengers.sort(Comparator.comparingInt((Person p)-> p.getSeat().row()));
    }

    public String toString() {
        return "FrontToBackStrategy";
    }
}
