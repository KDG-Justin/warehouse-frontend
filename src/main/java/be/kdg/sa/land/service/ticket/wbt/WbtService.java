package be.kdg.sa.land.service.ticket.wbt;


import be.kdg.sa.land.domain.ticket.WBT;
import be.kdg.sa.land.repository.ticket.WbtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WbtService {
    private final WbtRepository wbtRepository;
    private final Logger logger = LoggerFactory.getLogger(WbtService.class);

    public WbtService(WbtRepository wbtRepository) {
        this.wbtRepository = wbtRepository;
    }

    @Transactional
    public WBT createWBT(WBT wbt){
        logger.info("   Creating WBT for truck {}", wbt.getTruck().getLicensePlate());
        return wbtRepository.save(wbt);
    }

    @Transactional
    public WBT findWBTByTruck_LicensePlate(String licensePlate){
        logger.info("   Finding WBT by license plate {}", licensePlate);
        return wbtRepository.findWBTByTruck_LicensePlate(licensePlate);
    }
}
