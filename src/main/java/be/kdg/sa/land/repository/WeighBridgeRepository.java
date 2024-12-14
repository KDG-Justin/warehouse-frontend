package be.kdg.sa.land.repository;

import be.kdg.sa.land.domain.WeighBridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeighBridgeRepository extends JpaRepository<WeighBridge, Integer> {

    WeighBridge findByWBN(int wbn);
}
