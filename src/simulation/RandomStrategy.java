package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random random = new Random();

    @Override
    public void sortPassengers(ArrayList<Person> passengers) {
        Collections.shuffle(passengers, random);
    }

    public String toString() {
        return "RandomStrategy";
    }
}
