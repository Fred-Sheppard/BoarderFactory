package simulation;

public class Passenger {
    private String passengerName;
    private Seat seat;

    public Passenger(String passengerName, Seat seat) {
        this.passengerName = passengerName;
        this.seat = seat;
    }
    public Seat getSeat(){
    return seat;
    }
}
