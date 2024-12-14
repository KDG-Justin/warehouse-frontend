package be.kdg.sa.land.repository.ticket;

import be.kdg.sa.land.domain.ticket.WBT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WbtRepository extends JpaRepository<WBT, UUID> {

    WBT findWBTByTruck_LicensePlate(String licensePlate);
}
