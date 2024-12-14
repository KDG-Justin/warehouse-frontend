package be.kdg.sa.land.controller.dto.truck;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class DeliveryDto {
    private UUID id;
    private LocalDateTime deliveryTime;
    private String materialType;
    private UUID warehouseId;
    private BigDecimal amount;

    public DeliveryDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
