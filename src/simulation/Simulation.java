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
            System.out.println("\n=== Tick " + tick + " ===");
            
            // Print cage status
            System.out.println("Cage (" + cage.size() + " people):");
            for (Person p : cage) {
                System.out.println("  - Person for seat " + p.getSeat());
            }
            
            // Print aisle status
            System.out.println("\nAisle:");
            for (int i = 0; i < aisle.length; i++) {
                if (aisle[i] != null) {
                    Person p = aisle[i];
                    String status = p.isStowingBags() ? " (stowing bags, counter: " + p.getCounter() + ")" : 
                                  p.isSeated() ? " (seated)" : " (walking)";
                    System.out.println("  Position " + i + ": Person for seat " + p.getSeat() + status);
                } else {
                    System.out.println("  Position " + i + ": empty");
                }
            }

            // Try to move a person from cage to aisle if first position is empty
            if (!cage.isEmpty() && aisle[0] == null) {
                Person p = cage.remove(0);
                p.setX(0);
                aisle[0] = p;
                System.out.println("\nMoved new person to aisle position 0 (heading to seat " + p.getSeat() + ")");
            }

            for (int i = aisle.length - 1; i >= 0; i--) {
                if (aisle[i] == null) {
                    continue;
                }
                Person p = aisle[i];
                if (isPassengerAtSeat(p)) {
                    if (isPassengerReadyToSitDown(p)) {
                        aisle[i] = null;
                        seatedPassengers++;
                        p.setSeated(true);
                        System.out.println("Person at position " + i + " sat down at their seat " + p.getSeat());
                    } else if (p.isStowingBags()) {
                        int oldCounter = p.getCounter();
                        p.decrementCounter();
                        System.out.println("Person at position " + i + " is stowing bags (counter decreased from " + oldCounter + " to " + p.getCounter() + ")");
                    } else {
                        p.startStowingBags();
                        System.out.println("Person at position " + i + " started stowing bags (counter initialized to " + p.getCounter() + ")");
                    }
                } else if (i < aisle.length - 1 && aisle[i + 1] == null) {
                    aisle[i + 1] = p;
                    aisle[i] = null;
                    p.setX(p.getX() + 1);
                    System.out.println("Person moved from position " + i + " to " + (i + 1));
                }
            }
            
            tick++;
            paintGui();
            
            // Add a small delay to make it easier to follow
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
