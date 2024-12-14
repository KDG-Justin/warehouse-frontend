package be.kdg.sa.land.repository.truck;

import be.kdg.sa.land.domain.truck.TruckDeparture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TruckDepartureRepository extends JpaRepository<TruckDeparture, UUID> {

    TruckDeparture findTruckDepartureByTruckLicensePlate(String truckLicensePlate);
}
