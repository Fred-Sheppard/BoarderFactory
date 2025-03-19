package test.unit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import simulation.Seat;

class SeatTest {
    @Test
    void testSeatCreation() {
        Seat seat = new Seat(3, 2);
        assertEquals(3, seat.row(), "Row should match constructor argument");
        assertEquals(2, seat.col(), "Column should match constructor argument");
    }

    @Test
    void testSeatEquality() {
        Seat seat1 = new Seat(1, 2);
        Seat seat2 = new Seat(1, 2);
        Seat seat3 = new Seat(2, 1);

        assertEquals(seat1, seat2, "Same row and column seats should be equal");
        assertNotEquals(seat1, seat3, "Different row and column seats should not be equal");
        assertEquals(seat1.hashCode(), seat2.hashCode(), "Equal seats should have same hash code");
    }

    @Test
    void testToString() {
        Seat seat = new Seat(1, 2);
        String seatString = seat.toString();
        assertTrue(seatString.contains("1"), "ToString should contain row number");
        assertTrue(seatString.contains("2"), "ToString should contain column number");
    }
}
