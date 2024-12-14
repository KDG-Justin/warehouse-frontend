package be.kdg.sa.land.controller.dto;

import be.kdg.sa.land.domain.enums.MaterialType;
import be.kdg.sa.land.domain.customs.AppointmentTimeRange;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;


public class AppointmentDto {
    private UUID id;

    @AppointmentTimeRange
    private LocalDateTime arrivalTime;

    @Size(min=3,max=60,message="Name must be between 3 and 60 characters")
    @NotBlank(message = "Name can not be empty")
    private String sellerName = "";

    @NotBlank(message = "Address can not be empty")
    private String sellerAddress = "";

    @Size(min=3,max=60,message="License plate has at least 3 characters")
    private String truckLicensPlate = "";

    private MaterialType materialType;


    public AppointmentDto() {}



    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public @Size(min = 3, max = 60, message = "Namen moeten tussen 3 en 60 tekens zijn") @NotBlank(message = "Naam kan niet leeg zijn") String getSellerName() {
        return sellerName;
    }

    public void setSellerName(@Size(min = 3, max = 60, message = "Namen moeten tussen 3 en 60 tekens zijn") @NotBlank(message = "Naam kan niet leeg zijn") String sellerName) {
        this.sellerName = sellerName;
    }

    public @NotBlank(message = "Adres kan niet leeg zijn") String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(@NotBlank(message = "Adres kan niet leeg zijn") String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public @Size(min = 3, max = 60, message = "Namen moeten tussen 3 en 60 tekens zijn") String getTruckLicensPlate() {
        return truckLicensPlate;
    }

    public void setTruckLicensPlate(@Size(min = 3, max = 60, message = "Namen moeten tussen 3 en 60 tekens zijn") String truckLicensPlate) {
        this.truckLicensPlate = truckLicensPlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType (MaterialType materialType) {
        this.materialType = materialType;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
