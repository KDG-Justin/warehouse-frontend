package be.kdg.sa.land.controller.dto.truck;

import jakarta.validation.constraints.NotBlank;

public record DeliveryRequest(
        @NotBlank String licensePlate
){
}
