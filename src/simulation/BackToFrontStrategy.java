package simulation;
import java.util.*;
import java.util.Comparator;

public class BackToFrontStrategy implements Strategy{
    @Override
    public void sortPassengers(ArrayList<Person> passengers){
        passengers.sort(Comparator.comparingInt((Person p)-> p.getSeat().row()).reversed());
    }

    public String toString() {
        return "BackToFrontStrategy";
    }
}
