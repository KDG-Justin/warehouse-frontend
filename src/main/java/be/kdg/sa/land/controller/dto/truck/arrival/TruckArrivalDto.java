package be.kdg.sa.land.controller.dto.truck.arrival;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TruckArrivalDto {
    private String truckLicensePlate;
    private String weighBridge;
    private LocalDateTime arrivalTime;
    private BigDecimal currentWeight;
    private UUID warehouseUUID;

    public TruckArrivalDto() {
    }

    public String getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(String truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public String getWeighBridge() {
        return weighBridge;
    }

    public void setWeighBridge(String weighBridge) {
        this.weighBridge = weighBridge;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }

    public UUID getWarehouseUUID() {
        return warehouseUUID;
    }

    public void setWarehouseUUID(UUID warehouseUUID) {
        this.warehouseUUID = warehouseUUID;
    }
}
