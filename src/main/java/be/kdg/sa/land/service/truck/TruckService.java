package be.kdg.sa.land.service.truck;

import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.repository.truck.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TruckService {
    private final TruckRepository truckRepository;
    private static final Logger logger = LoggerFactory.getLogger(TruckService.class);

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Truck> findTruckByLicensePlate(String licensePlate){
        logger.info("   Getting Truck with license plate: {}", licensePlate);
        return truckRepository.findByLicensePlate(licensePlate);
    }

    @Transactional
    public Truck create(Truck truck){
        logger.info("   Creating Truck: {}", truck.getLicensePlate());
        return truckRepository.save(truck);
    }
}
