package be.kdg.sa.land.service.delivery;

import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Delivery> findDeliveryByTruck_LicensePlate(String licensePlate) {
        logger.info("   Finding Delivery material with license plate: {}", licensePlate);
        return deliveryRepository.findDeliveryByTruck_LicensePlate(licensePlate);
    }

    @Transactional(readOnly = true)
    public Delivery findDeliveryByDeliveryTime(LocalDateTime deliveryTime) {
        logger.info("   Finding Delivery material with time {}", deliveryTime);
        return deliveryRepository.findDeliveryByDeliveryTime(deliveryTime);
    }

    @Transactional
    public void create(Delivery delivery) {
        logger.info("   Creating delivery with material: {}", delivery.getMaterialType());
        deliveryRepository.save(delivery);
    }
}
