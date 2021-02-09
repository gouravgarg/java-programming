package org.gourav.flight;

public class EconomyFlight extends Flight {

    String flightType;

    public EconomyFlight(String flightType) {
        super.setFlightType(flightType);
        this.flightType = flightType;
    }

    public boolean addPassenger(Passenger passenger) {
        onboarding.onBoard(passenger);
        return true;
    }

    public boolean removePassenger(Passenger passenger) {
        if (passenger.vip())
            return false;
        return disembark.debark(passenger);
    }
}
