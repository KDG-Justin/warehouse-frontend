package be.kdg.sa.land.service.amqp;

import be.kdg.sa.land.config.RabbitTopology;
import be.kdg.sa.land.controller.dto.truck.DeliveryDto;
import be.kdg.sa.land.service.delivery.UpdateDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SendMaterialAmountToWarehouseService {
    private final RabbitTemplate rabbitTemplate;
    private final UpdateDeliveryService updateDeliveryService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SendMaterialAmountToWarehouseService(RabbitTemplate rabbitTemplate,
                                               UpdateDeliveryService updateDeliveryService) {
        this.rabbitTemplate = rabbitTemplate;
        this.updateDeliveryService = updateDeliveryService;
    }

    @Transactional
    public void sendMaterialAmountToWarehouse(String licensePlate, LocalDateTime deliveryTime){
        logger.info("   Sending material amount of delivery {}:{}", deliveryTime.getHour(), deliveryTime.getMinute());
        DeliveryDto deliveryDto = updateDeliveryService.updateDelivery(licensePlate, deliveryTime);
        rabbitTemplate.convertAndSend(RabbitTopology.DIRECT_EXCHANGE, "material.queue", deliveryDto);

    }

    @Transactional
    public void sendFifoMaterialAmountToWarehouse(String licensePlate, LocalDateTime deliveryTime){
        logger.info("   Sending material amount of FIFO delivery {}:{}", deliveryTime.getHour(), deliveryTime.getMinute());
        DeliveryDto deliveryDto = updateDeliveryService.updateDelivery(licensePlate, deliveryTime);
        rabbitTemplate.convertAndSend(RabbitTopology.DIRECT_EXCHANGE, "fifo.queue", deliveryDto);

    }
}
