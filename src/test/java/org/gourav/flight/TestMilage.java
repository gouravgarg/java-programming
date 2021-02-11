package org.gourav.flight;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestMilage {

    Milage milage ;
    Passenger usualPassengerSaytam = null;
    Passenger vipPassengerShashi = null;

    @BeforeAll
    void setup(){
        milage=new Milage();
        usualPassengerSaytam = new Passenger("Satyam", false);
        vipPassengerShashi = new Passenger("Shashi", true);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings ={"e;Satyam;false;400","e;Shashi;true;400","b;Satyam;false;1000","b;Shashi;true;1000"
            ,"p;Satyam;false;1600","p;Shashi;true;1600"} )
    void testAddMilage(@ConvertWith(FlightArgumentConverter.class) Flight flight){
        for (Passenger passenger: flight.getPassengerSet()) {
            milage.addMilage(passenger,flight.getDistance());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/flight_information.csv")
    void testAddMilageWithCSV(@ConvertWith(FlightArgumentConverter.class) Flight flight){
        for (Passenger passenger: flight.getPassengerSet()) {
            milage.addMilage(passenger,flight.getDistance());
        }
        // System.out.println(milage.getPassengerMilageMap());
    }

//    @AfterAll
//    void testCalculateGivenPoint(){
//        milage.calculateGivenPoint();
//        Assertions.assertEquals(20,milage.getPassengerPointMap().get(usualPassengerSaytam));
//        Assertions.assertEquals(300,milage.getPassengerPointMap().get(vipPassengerShashi));
//        System.out.println(milage.getPassengerPointMap());
//    }

}
