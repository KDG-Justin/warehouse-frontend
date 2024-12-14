package be.kdg.sa.land.controller.dto.truck.departure;


import be.kdg.sa.land.domain.WeighBridge;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TruckDepartureDto {
    private String truck;
    private WeighBridge weighBridge;
    private LocalDateTime departureTime;
    private BigDecimal currentWeight;

    public TruckDepartureDto() {}

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public WeighBridge getWeighBridge() {
        return weighBridge;
    }

    public void setWeighBridge(WeighBridge weighBridge) {
        this.weighBridge = weighBridge;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }
}
