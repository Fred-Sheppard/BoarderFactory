package simulation;

import ui.AbstractGuiFactory;
import simulation.BasicPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

// Note: Simulation shouldn't have any knowledge of the UI being used
// That means no console logging or reading inputs
public class Simulation {
    /**
     * @param rows       The number of rows of seats
     * @param cols       The number of columns in each row
     * @param strategies List of boarding strategies to be executed
     * @param guiFactory If empty, the simulation is run with no visuals
     */
    private int rows;
    private int cols;
    private List<Strategy> strategies;
    private Optional<AbstractGuiFactory> guiFactory;
    private ArrayList<Person> cage = new ArrayList<>();
    private Person[] aisle;
    public Simulation(int rows, int cols, List<Strategy> strategies, Optional<AbstractGuiFactory> guiFactory) {
        this.rows = rows;
        this.cols = cols;
        this.strategies = strategies;
        this.guiFactory = guiFactory;
        aisle = new Person[rows];
    }
    private void setup(Strategy strategy) {
        for (int i=0; i < rows; i++) {
            for (int j=0; j < cols; j++) {
                Seat seat = new Seat(i, j);
                Person p = new BasicPerson(seat);
                Random random = new Random();
                int r1 = random.nextInt(2), r2 = random.nextInt(2), r3 = random.nextInt(2);
                if (r1 == 1){
                    p = new DutyFreeBag(p);
                }if (r2 == 1){
                    p = new OverHeadBag(p);
                }if (r3 == 1){
                    p = new UnderBag(p);
                }
                cage.add(p);
            }
        }
        strategy.sortPassengers(cage);
    }
    private void movePerson(Person p, int index) {
        if (aisle[index] != null) {
            throw new RuntimeException("Someone is already in the " + index + " position");
        }
        aisle[index] = p;
    }
    private SimulationResults mainLoop(){
        for (int i = 0; i < aisle.length; i++) {
            if (aisle[i] == null) {
                System.out.println("NO PASSENGER");
                continue;
            }
            if (aisle[i].getX() == aisle[i].getSeat().row()) {
                if (aisle[i].isStowingBags() && aisle[i].getCounter() == 0) {
                    aisle[i] = null;
                    System.out.println("Sitting down");
                } else if (aisle[i].isStowingBags()) {
                    aisle[i].decrementCounter();
                } else {
                    aisle[i].startStowingBags();
                }
            }
            aisle[i].setX(aisle[i].getX() + 1);
            movePerson(aisle[i], aisle[i].getX() + 1);
        }
        return new SimulationResults();
    }

    public SimulationResults run() {
        for (Strategy strategy : strategies) {
            setup(strategy);
            mainLoop();
            // clean up here
        }
       return new SimulationResults();
    }
}
