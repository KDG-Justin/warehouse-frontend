package be.kdg.sa.land.service.amqp;

import be.kdg.sa.land.config.RabbitTopology;
import be.kdg.sa.land.controller.dto.SellerDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendSellerToWarehouseService {
    private final RabbitTemplate rabbitTemplate;

    public SendSellerToWarehouseService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public void sendSellerToWarehouse(SellerDto sellerDto) {
        rabbitTemplate.convertAndSend(RabbitTopology.DIRECT_EXCHANGE, "seller.queue", sellerDto);
    }
}
