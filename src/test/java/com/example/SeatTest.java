package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeatTest {

    @Test
    public void testIsSeatAvailable_whenSeatIsFree_returnsTrue() {
        Seat seat = new Seat();
        assertTrue(seat.isSeatAvailable(1, 1));
    }
}