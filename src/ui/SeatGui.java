package ui;

import simulation.Seat;

public interface SeatGui {
    void paintFull(Seat seat, int seatsPerRow);

    void paintEmpty(Seat seat, int seatsPerRow);
}
