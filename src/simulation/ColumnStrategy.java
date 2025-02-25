package simulation;
import java.util.*;
import java.util.Comparator;

public class ColumnStrategy implements Strategy {
    public void sortPassengers(ArrayList<Passenger> passengers) {

        passengers.sort(Comparator.comparingInt((Passenger p) -> p.getSeat().col())
                .thenComparingInt(p -> p.getSeat().row()).reversed());
    }

}
