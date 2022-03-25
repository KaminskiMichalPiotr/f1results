package com.f1.f1results.entities.driverresult;

import org.junit.jupiter.api.Test;

import static com.f1.f1results.entities.driverresult.PointsScoringSystem.getPointsScoredByPosition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointsScoringSystemTest {

    //1 position equals 25 points with full race distance
    @Test
    void checkWithPositionInsidePoints() {
        assertEquals(25, getPointsScoredByPosition(1, RaceDistance.FULL));
    }

    //Position outside top 10 is 0 points
    @Test
    void checkWithPositionOutsidePoints() {
        assertEquals(0, getPointsScoredByPosition(20, RaceDistance.FULL));
    }

    //1 position equals 12.5 points with full race distance
    @Test
    void checkWithHalfRaceDistance() {
        assertEquals(12.5, getPointsScoredByPosition(1, RaceDistance.HALF));
    }

    @Test
    void checkWithNullRaceDistance() {
        assertThrows(NullPointerException.class, () -> getPointsScoredByPosition(20, null));
    }

    @Test
    void checkWithPositionZero() {
        assertThrows(IllegalArgumentException.class, () -> getPointsScoredByPosition(0, RaceDistance.FULL));
    }

}