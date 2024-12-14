package be.kdg.sa.land.service;

import be.kdg.sa.land.domain.WeighBridge;
import be.kdg.sa.land.repository.WeighBridgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeighBridgeService {
    private final WeighBridgeRepository weighBridgeRepository;
    private final Logger logger = LoggerFactory.getLogger(WeighBridgeService.class);

    public WeighBridgeService(WeighBridgeRepository weighBridgeRepository) {
        this.weighBridgeRepository = weighBridgeRepository;
    }

    @Transactional(readOnly = true)
    public WeighBridge findWeighBridge(int wbn) {
        logger.info("   Getting Weigh Bridge with number {}", wbn);
        return weighBridgeRepository.findByWBN(wbn);
    }
}
