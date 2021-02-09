package org.gourav.flight;

import java.util.HashMap;
import java.util.Map;

public class Milage {

    public static final int VIP_FACTOR=10;
    public static final int USUAL_FACTOR=20;

    Map<Passenger, Long> passengerMilageMap= new HashMap<>();
    Map<Passenger, Long> passengerPointMap= new HashMap<>();

    public Map<Passenger, Long> getPassengerPointMap() {
        return passengerPointMap;
    }


    public void addMilage(Passenger passenger, long miles){
       if(passengerMilageMap.containsKey(passenger))
           miles = passengerMilageMap.get(passenger).longValue() + miles;
        passengerMilageMap.put(passenger,miles);
    }

    public void calculateGivenPoint(){
        for (Passenger passenger: passengerMilageMap.keySet()) {
            if(passenger.vip())
                passengerPointMap.put(passenger,passengerMilageMap.get(passenger).longValue()/VIP_FACTOR);
            else
                passengerPointMap.put(passenger,passengerMilageMap.get(passenger).longValue()/USUAL_FACTOR);
        }
    }

}
