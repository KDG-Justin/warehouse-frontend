package be.kdg.sa.land.controller.dto.truck.departure;

import jakarta.validation.constraints.NotBlank;

public record CreateTruckDepartureRequest(
        @NotBlank String truckLicensePlate
) {

}
