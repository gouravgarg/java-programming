package org.gourav.flight;

public class PremiumFlight extends Flight {

    String flightType;

    public PremiumFlight(String flightType) {
        super.setFlightType(flightType);
        this.flightType = flightType;
    }

    public boolean addPassenger(Passenger passenger) {
      if(passenger.vip())
          return onboarding.onBoard(passenger);
      return false;

    }

    public boolean removePassenger(Passenger passenger) {
        return disembark.debark(passenger);

    }

}
