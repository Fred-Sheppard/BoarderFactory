package simulation;

import ui.AbstractGuiFactory;
import ui.AisleGui;
import ui.PersonGui;
import ui.SeatGui;
import interceptor.Dispatcher;
import interceptor.Context;

import java.util.*;

// Note: Simulation shouldn't have any knowledge of the UI being used
// That means no console logging or reading inputs
public class Simulation {
    private final int rows;
    private final int cols;
    private final List<Strategy> strategies;
    private final ArrayList<Person> cage = new ArrayList<>();
    private final Person[] aisle;
    private final PersonGui personGui;
    private final AisleGui aisleGui;
    private final SeatGui seatGui;
    private final ArrayList<Seat> filledSeats = new ArrayList<>();

    /**
     * @param rows       The number of rows of seats
     * @param cols       The number of columns in each row
     * @param strategies List of boarding strategies to be executed
     * @param guiFactory If empty, the simulation is run with no visuals
     */
    public Simulation(int rows, int cols, List<Strategy> strategies, Optional<AbstractGuiFactory> guiFactory) {
        Context context = Dispatcher.before("Simulation.constructor", rows, cols, strategies, guiFactory);
        this.rows = rows;
        this.cols = cols;
        this.strategies = strategies;
        aisle = new Person[rows];
        // TODO
        AbstractGuiFactory factory = guiFactory.get();
        this.personGui = factory.createPersonGui();
        this.aisleGui = factory.createAisleGui();
        this.seatGui = factory.createSeatGui();
        Dispatcher.after(context, null);
    }

    private void setup(Strategy strategy) {
        Context context = Dispatcher.before("Simulation.setup", strategy);
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
        Dispatcher.after(context, null);
    }

    private void movePerson(Person p, int index) {
        Context context = Dispatcher.before("Simulation.movePerson", p, index);
        if (aisle[index] != null) {
            Dispatcher.after(context, null);
            return;
        }
        aisle[index] = p;
        aisle[index - 1] = null;
        p.setX(index);
        Dispatcher.after(context, null);
    }

    private int mainLoop() {
        Context context = Dispatcher.before("Simulation.mainLoop");
        int tick = 0;
        int passengers = cage.size();
        while (filledSeats.size() < passengers) {
            // Try to move a person from cage to aisle if first position is empty
            if (!cage.isEmpty() && aisle[0] == null) {
                Person p = cage.removeFirst();
                p.setX(0);
                aisle[0] = p;
                // TODO don't move him on
            }

            for (int i = aisle.length - 1; i >= 0; i--) {
                if (aisle[i] == null) {
                    continue;
                }
                Person p = aisle[i];
                if (isPassengerAtSeat(p)) {
                    if (isPassengerReadyToSitDown(p)) {
                        aisle[i] = null;
                        filledSeats.add(p.getSeat());
                        p.setSeated(true);
                    } else if (p.isStowingBags()) {
                        p.decrementCounter();
                    } else {
                        p.startStowingBags();
                    }
                } else {
                    movePerson(p, p.getX() + 1);
                }
            }
            paintGui();
            tick++;
        }
        Dispatcher.after(context, tick);
        return tick;
    }

    private void paintGui() {
        Context context = Dispatcher.before("Simulation.paintGui");
        aisleGui.paint(this.rows);
        for (Person p : aisle) {
            if (p == null) continue;
            personGui.paint(p);
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Seat seat = new Seat(row, col);
                if (filledSeats.contains(seat)) {
                    seatGui.paintFull(new Seat(row, col), cols);
                } else {
                    seatGui.paintEmpty(new Seat(row, col), cols);
                }
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Dispatcher.after(context, null);
    }

    private boolean isPassengerAtSeat(Person p) {
        Context context = Dispatcher.before("Simulation.isPassengerAtSeat", p);
        boolean result = p.getX() == p.getSeat().row();
        Dispatcher.after(context, result);
        return result;
    }

    private boolean isPassengerReadyToSitDown(Person p) {
        Context context = Dispatcher.before("Simulation.isPassengerReadyToSitDown", p);
        boolean result = p.isStowingBags() && p.getCounter() == 0;
        Dispatcher.after(context, result);
        return result;
    }

    public SimulationResults run() {
        Context context = Dispatcher.before("Simulation.run");
        HashMap<Strategy, Integer> results = new HashMap<>();
        for (Strategy strategy : strategies) {
            setup(strategy);
            int timeTaken = mainLoop();
            results.put(strategy, timeTaken);
        }
        SimulationResults simulationResults = new SimulationResults(results);
        Dispatcher.after(context, simulationResults);
        return simulationResults;
    }
}
