package be.kdg.sa.land.repository.truck;


import be.kdg.sa.land.domain.truck.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TruckRepository extends JpaRepository<Truck, String> {
    Optional<Truck> findByLicensePlate(String licensePlate);
}
