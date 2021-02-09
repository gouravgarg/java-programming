package org.gourav.flight;

import org.gourav.flight.*;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class FlightArgumentConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        org.junit.jupiter.api.Assertions.assertEquals(String.class,source.getClass(),"Can only convert from String");
        org.junit.jupiter.api.Assertions.assertEquals(Flight.class,targetType,"Can only to flight");

        String[] flightInput = source.toString().split(";");
        Flight flight = null;

        switch (flightInput[0].toLowerCase().trim()){
            case "e":
                flight=new EconomyFlight("Economy");
                break;
            case "b":
                flight=new BusinessFlight("Business");
                break;
            case "p":
                flight=new PremiumFlight("Premium");
                break;
        }
        flight.addPassenger(new Passenger(flightInput[1].trim(),Boolean.valueOf(flightInput[2].trim()).booleanValue()));
        flight.setDistance(Long.valueOf(flightInput[3]).longValue());
        return flight;
    }
}
