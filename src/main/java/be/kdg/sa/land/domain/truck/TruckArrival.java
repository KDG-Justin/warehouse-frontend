package be.kdg.sa.land.domain.truck;


import be.kdg.sa.land.domain.WeighBridge;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TruckArrival {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Truck truck;

    @ManyToOne
    private WeighBridge weighBridge;

    private LocalDateTime arrivalTime;

    private BigDecimal currentWeight;

    private UUID warehouse;

    protected TruckArrival() {}

    public TruckArrival(Truck truck, LocalDateTime arrivalTime, WeighBridge weighBridge, BigDecimal currentWeight, UUID warehouse) {
        this.truck = truck;
        this.arrivalTime = arrivalTime;
        this.weighBridge = weighBridge;
        this.currentWeight = currentWeight;
        this.warehouse = warehouse;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WeighBridge getWeighBridge() {
        return weighBridge;
    }

    public void setWeighBridge(WeighBridge weighBridge) {
        this.weighBridge = weighBridge;
    }

    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }

    public UUID getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(UUID warehouse) {
        this.warehouse = warehouse;
    }
}
