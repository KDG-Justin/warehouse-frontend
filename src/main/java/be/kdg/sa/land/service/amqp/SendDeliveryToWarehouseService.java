package be.kdg.sa.land.service.amqp;

import be.kdg.sa.land.config.RabbitTopology;
import be.kdg.sa.land.controller.dto.truck.DeliveryDto;
import be.kdg.sa.land.service.delivery.FindDeliveryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendDeliveryToWarehouseService {
    private final FindDeliveryService findDeliveryService;
    private final RabbitTemplate rabbitTemplate;

    public SendDeliveryToWarehouseService(FindDeliveryService findDeliveryService, RabbitTemplate rabbitTemplate) {
        this.findDeliveryService = findDeliveryService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public DeliveryDto sendDeliveryToWarehouse(String licensePlate){
        DeliveryDto deliveryDto = findDeliveryService.findDeliveryByTruckLicensePlate(licensePlate);
        rabbitTemplate.convertAndSend(RabbitTopology.DIRECT_EXCHANGE, "delivery.queue", deliveryDto);
        return deliveryDto;
    }

    @Transactional
    public void sendFifoDeliveryToWarehouse(String licensePlate){
        DeliveryDto deliveryDto = findDeliveryService.findDeliveryByTruckLicensePlate(licensePlate);
        rabbitTemplate.convertAndSend(RabbitTopology.DIRECT_EXCHANGE, "fifo.queue", deliveryDto);
    }
}

