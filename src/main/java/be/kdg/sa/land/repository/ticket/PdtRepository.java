package be.kdg.sa.land.repository.ticket;

import be.kdg.sa.land.domain.ticket.PDT;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PdtRepository extends JpaRepository<PDT, UUID> {
}
