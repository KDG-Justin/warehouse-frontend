package be.kdg.sa.land.repository;

import be.kdg.sa.land.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {


    Delivery findDeliveryByDeliveryTime(LocalDateTime deliveryTime);

    Optional<Delivery> findDeliveryByTruck_LicensePlate(String licensePlate);
}
