package be.kdg.sa.land.repository.truck;


import be.kdg.sa.land.domain.truck.TruckArrival;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TruckArrivalRepository extends JpaRepository<TruckArrival, UUID> {

    TruckArrival findTruckArrivalByTruckLicensePlate(String truckLicensePlate);
}
