package be.kdg.sa.land.service.truck.departure;

import be.kdg.sa.land.domain.truck.TruckDeparture;
import be.kdg.sa.land.repository.truck.TruckDepartureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TruckDepartureService {
    private final TruckDepartureRepository truckDepartureRepository;
    private static final Logger logger = LoggerFactory.getLogger(TruckDepartureService.class);


    public TruckDepartureService(TruckDepartureRepository truckDepartureRepository) {
        this.truckDepartureRepository = truckDepartureRepository;
    }

    @Transactional
    public TruckDeparture findTruckDepartureByTruckLicensePlate(String truckLicensePlate) {
        logger.info("   Getting truck departure with license plate: {}", truckLicensePlate);
        return truckDepartureRepository.findTruckDepartureByTruckLicensePlate(truckLicensePlate);
    }

    @Transactional
    public TruckDeparture createTruckDeparture(TruckDeparture truckDeparture) {
        logger.info("   Creating truck departure for {} departured at {}:{}", truckDeparture.getTruck().getLicensePlate(), truckDeparture.getDepartureTime().getHour(), truckDeparture.getDepartureTime().getMinute());
        return truckDepartureRepository.save(truckDeparture);
    }
}
