package org.gourav.flight;

import java.util.HashSet;
import java.util.Set;

public abstract class  Flight {

    private String flightType;
    private Set<Passenger> passengerSet = new HashSet<>();
    private long distance;

    protected OnBoarding onboarding = (passenger -> getPassengerSet().add(passenger));
    protected Disembark disembark = (passenger -> getPassengerSet().remove(passenger));

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public Set<Passenger> getPassengerSet() {
        return passengerSet;
    }

    public abstract  boolean addPassenger(Passenger passenger);

    public abstract  boolean removePassenger(Passenger passenger);

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightType='" + flightType + '\'' +
                ", passengerSet=" + passengerSet +
                '}';
    }
}
