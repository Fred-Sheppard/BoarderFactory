package simulation;

import java.util.ArrayList;
import java.util.Comparator;

public class FrontToBackStrategy implements Strategy {
    @Override
    public void sortPassengers(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparingInt((Passenger p)-> p.getSeat().row()));
    }
}
