package be.kdg.sa.land.service.delivery;

import be.kdg.sa.land.controller.dto.truck.DeliveryDto;
import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.ticket.WBT;
import be.kdg.sa.land.service.ticket.wbt.WbtService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UpdateDeliveryService {
    private final DeliveryService deliveryService;
    private final WbtService wbtService;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(UpdateDeliveryService.class);

    public UpdateDeliveryService(DeliveryService deliveryService, WbtService wbtService, ModelMapper modelMapper) {
        this.deliveryService = deliveryService;
        this.wbtService = wbtService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public DeliveryDto updateDelivery(String licensePlate, LocalDateTime deliveryTime) {
        WBT wbt = wbtService.findWBTByTruck_LicensePlate(licensePlate);
        logger.info("   Updating warehouse -> Material Amount = {}", wbt.getMaterialWeight());

        Delivery delivery = deliveryService.findDeliveryByDeliveryTime(deliveryTime);
        delivery.setAmount(new BigDecimal(String.valueOf(wbt.getMaterialWeight())));

        return modelMapper.map(delivery, DeliveryDto.class);
    }
}
