package simulation.strategy;

import simulation.passenger.Person;

import java.util.*;
import java.util.Comparator;

public class ColumnStrategy implements Strategy {
    public void sortPassengers(ArrayList<Person> passengers) {

        passengers.sort(Comparator.comparingInt((Person p) -> p.getSeat().col())
                .thenComparingInt(p -> p.getSeat().row()).reversed());
    }

    public String toString() {
        return "ColumnStrategy";
    }
}
