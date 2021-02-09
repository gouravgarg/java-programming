package org.gourav.flight;

public class BusinessFlight extends Flight {

    String flightType;

    public BusinessFlight(String flightType) {
        super.setFlightType(flightType);
        this.flightType = flightType;
    }

    public boolean addPassenger(Passenger passenger) {
        if (passenger.vip())
            return onboarding.onBoard(passenger);
        return false;
    }

    public boolean removePassenger(Passenger passenger) {
        return false;

    }
}
