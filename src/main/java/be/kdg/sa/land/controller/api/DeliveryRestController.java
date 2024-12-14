package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.truck.DeliveryDto;
import be.kdg.sa.land.controller.dto.truck.DeliveryRequest;
import be.kdg.sa.land.service.amqp.SendDeliveryToWarehouseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryRestController {
    private final SendDeliveryToWarehouseService sendDeliveryToWarehouseService;

    public DeliveryRestController(SendDeliveryToWarehouseService sendDeliveryToWarehouseService) {
        this.sendDeliveryToWarehouseService = sendDeliveryToWarehouseService;
    }

    @PostMapping("")
    public DeliveryDto createDeliveryForWarehouse(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        return sendDeliveryToWarehouseService.sendDeliveryToWarehouse(deliveryRequest.licensePlate());
    }
}
