package be.kdg.sa.land.domain.ticket;


import be.kdg.sa.land.domain.enums.MaterialType;
import be.kdg.sa.land.domain.truck.Truck;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PDT {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @OneToOne
    private Truck truck;

    private UUID warehouseId;

    private LocalDateTime deliveryTime;


    protected PDT() {}

    public PDT(MaterialType materialType, Truck truck, UUID warehouseId, LocalDateTime deliveryTime) {
        this.materialType = materialType;
        this.truck = truck;
        this.warehouseId = warehouseId;
        this.deliveryTime = deliveryTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getWarehouse() {
        return warehouseId;
    }

    public void setWarehouse(UUID warehouseId) {
        this.warehouseId = warehouseId;
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
}
