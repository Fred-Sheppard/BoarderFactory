package simulation;
import java.util.*;
import java.util.Comparator;

public class BackToFrontStrategy implements Strategy{
    @Override
    public void sortPassengers(ArrayList<Passenger> passengers){
        passengers.sort(Comparator.comparingInt((Passenger p)-> p.getSeat().row()).reversed());

    }
}
