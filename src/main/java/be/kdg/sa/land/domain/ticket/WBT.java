package be.kdg.sa.land.domain.ticket;

import be.kdg.sa.land.domain.truck.Truck;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class WBT {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Truck truck;

    private BigDecimal arrivalWeight;
    private BigDecimal materialWeight;
    private BigDecimal departureWeight;

    @Column(unique = true)
    private LocalDateTime weighingTime;

    protected WBT() {}

    public WBT(Truck truck, BigDecimal arrivalWeight, BigDecimal materialWeight, BigDecimal departureWeight, LocalDateTime weighingTime) {
        this.truck = truck;
        this.arrivalWeight = arrivalWeight;
        this.materialWeight = materialWeight;
        this.departureWeight = departureWeight;
        this.weighingTime = weighingTime;
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

    public BigDecimal getArrivalWeight() {
        return arrivalWeight;
    }

    public void setArrivalWeight(BigDecimal arrivalWeight) {
        this.arrivalWeight = arrivalWeight;
    }

    public BigDecimal getMaterialWeight() {
        return materialWeight;
    }

    public void setMaterialWeight(BigDecimal materialWeight) {
        this.materialWeight = materialWeight;
    }

    public BigDecimal getDepartureWeight() {
        return departureWeight;
    }

    public void setDepartureWeight(BigDecimal departureWeight) {
        this.departureWeight = departureWeight;
    }

    public LocalDateTime getWeighingTime() {
        return weighingTime;
    }

    public void setWeighingTime(LocalDateTime weighingTime) {
        this.weighingTime = weighingTime;
    }
}
