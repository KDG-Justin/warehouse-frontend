package be.kdg.sa.land.domain;

import be.kdg.sa.land.domain.enums.MaterialType;
import be.kdg.sa.land.domain.truck.Truck;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @ManyToOne
    private Truck truck;

    private LocalDateTime deliveryTime;
    private BigDecimal amount;

    private UUID warehouseId;

    protected Delivery() {}

    public Delivery(MaterialType materialType, Truck truck) {
        this.materialType = materialType;
        this.truck = truck;
    }


    public UUID getId() {
        return id;
    }


    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }


    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }
}
