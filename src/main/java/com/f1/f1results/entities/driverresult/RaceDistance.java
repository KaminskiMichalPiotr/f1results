package com.f1.f1results.entities.driverresult;

public enum RaceDistance {

    FULL(1d),
    HALF(0.5),
    QUARTER(0.25);


    private final double raceDistance;

    RaceDistance(double raceDistance) {
        this.raceDistance = raceDistance;
    }

    public double getRaceDistance() {
        return raceDistance;
    }
}
