package simulation;

import ui.AbstractGuiFactory;

import java.util.*;

// Note: Simulation shouldn't have any knowledge of the UI being used
// That means no console logging or reading inputs
public class Simulation {
    private final int rows;
    private final int cols;
    private final List<Strategy> strategies;
    private final ArrayList<Person> cage = new ArrayList<>();
    private final Person[] aisle;

    /**
     * @param rows       The number of rows of seats
     * @param cols       The number of columns in each row
     * @param strategies List of boarding strategies to be executed
     * @param guiFactory If empty, the simulation is run with no visuals
     */
    public Simulation(int rows, int cols, List<Strategy> strategies, Optional<AbstractGuiFactory> guiFactory) {
        this.rows = rows;
        this.cols = cols;
        this.strategies = strategies;
        aisle = new Person[rows];
        // TODO create GUI painters
    }

    private void setup(Strategy strategy) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Seat seat = new Seat(i, j);
                Person p = new BasicPerson(seat);
                Random random = new Random();
                int r1 = random.nextInt(2), r2 = random.nextInt(2), r3 = random.nextInt(2);
                if (r1 == 1) {
                    p = new DutyFreeBag(p);
                }
                if (r2 == 1) {
                    p = new OverHeadBag(p);
                }
                if (r3 == 1) {
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

    private int mainLoop() {
        int seatedPassengers = 0;
        int tick = 0;
        int passengers = cage.size();
        while (seatedPassengers < passengers) {
            for (int i = 0; i < aisle.length; i++) {
                if (aisle[i] == null) {
                    continue;
                }
                Person p = aisle[i];
                if (isPassengerAtSeat(p)) {
                    if (isPassengerReadyToSitDown(p)) {
                        aisle[i] = null;
                        seatedPassengers++;
                        p.setSeated(true);
                    } else if (p.isStowingBags()) {
                        p.decrementCounter();
                    } else {
                        p.startStowingBags();
                    }
                } else {
                    p.setX(p.getX() + 1);
                    movePerson(p, p.getX() + 1);
                }

                paintGui();
            }
        }
        return tick;
    }

    private void paintGui() {
        // TODO use Frizzle's code
    }

    private boolean isPassengerAtSeat(Person p) {
        return p.getX() == p.getSeat().row();
    }

    private boolean isPassengerReadyToSitDown(Person p) {
        return p.isStowingBags() && p.getCounter() == 0;
    }

    public SimulationResults run() {
        HashMap<Strategy, Integer> results = new HashMap<>();
        for (Strategy strategy : strategies) {
            setup(strategy);
            int timeTaken = mainLoop();
            results.put(strategy, timeTaken);
        }
        return new SimulationResults(results);
    }
}
