package be.kdg.sa.land.util;

import java.math.BigDecimal;
import java.util.Random;

public class Calculation {

    public static BigDecimal calculateTruckArrivalWeight(BigDecimal truckWeight){
        Random random = new Random();
        int randomAddition = random.nextInt(1, 20);
        BigDecimal randomWeight = new BigDecimal(randomAddition);

        return truckWeight.add(randomWeight);
    }

    public static BigDecimal calculateTruckDepartureWeight(BigDecimal truckWeight){
        Random random = new Random();
        int randomAddition = random.nextInt(1, truckWeight.intValue());
        BigDecimal randomWeight = new BigDecimal(randomAddition);

        return truckWeight.subtract(randomWeight);
    }

    public static BigDecimal calculateMaterialWeight(BigDecimal departureTruckWeight, BigDecimal arrivalTruckWeight){
        return arrivalTruckWeight.subtract(departureTruckWeight);
    }
}
