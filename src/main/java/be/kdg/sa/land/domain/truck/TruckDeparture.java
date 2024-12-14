package be.kdg.sa.land.domain.truck;

import be.kdg.sa.land.domain.WeighBridge;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TruckDeparture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Truck truck;

    @ManyToOne
    private WeighBridge weighBridge;

    private LocalDateTime departureTime;

    private BigDecimal currentWeight;


    protected TruckDeparture() {}

    public TruckDeparture(Truck truck, WeighBridge weighBridge, LocalDateTime departureTime, BigDecimal currentWeight) {
        this.truck = truck;
        this.weighBridge = weighBridge;
        this.departureTime = departureTime;
        this.currentWeight = currentWeight;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
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
