package be.kdg.sa.land.controller.dto.truck.arrival;

import be.kdg.sa.land.domain.enums.MaterialType;
import jakarta.validation.constraints.NotBlank;

public record CreateTruckArrivalRequest(
        @NotBlank String truckLicensePlate,
        @NotBlank String arrivalTime,
        MaterialType materialType
        ) {

}
