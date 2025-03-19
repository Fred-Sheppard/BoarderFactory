package simulation.strategy;

import simulation.passenger.Person;

import java.util.ArrayList;

public interface Strategy {
    void sortPassengers(ArrayList<Person> passengers);
}
