package be.kdg.sa.land.service.truck.arrival;

import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.repository.truck.TruckArrivalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TruckArrivalService {
    private final TruckArrivalRepository truckArrivalRepository;
    private static final Logger logger = LoggerFactory.getLogger(TruckArrivalService.class);

    public TruckArrivalService(TruckArrivalRepository truckArrivalRepository) {
        this.truckArrivalRepository = truckArrivalRepository;
    }

    @Transactional(readOnly = true)
    public TruckArrival findTruckArrivalByTruckLicensePlate(String licensePlate) {
        logger.info("   Getting truck {} that has arrived", licensePlate);
        return truckArrivalRepository.findTruckArrivalByTruckLicensePlate(licensePlate);
    }

    @Transactional
    public TruckArrival createArrival(TruckArrival truckArrival) {
        logger.info("   Creating truck arrival for {} arrived at {}:{}", truckArrival.getTruck().getLicensePlate(), truckArrival.getArrivalTime().getHour(), truckArrival.getArrivalTime().getMinute());
        return truckArrivalRepository.save(truckArrival);

    }
}
