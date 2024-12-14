package be.kdg.sa.land.service.truck.arrival;

import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.Seller;
import be.kdg.sa.land.domain.WeighBridge;
import be.kdg.sa.land.domain.enums.MaterialType;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.exception.AppointmentException;
import be.kdg.sa.land.service.appointment.CheckAppointmentService;
import be.kdg.sa.land.service.delivery.DeliveryService;
import be.kdg.sa.land.service.rest.FindWarehouseService;
import be.kdg.sa.land.service.seller.CreateRandomSellerService;
import be.kdg.sa.land.service.TerrainService;
import be.kdg.sa.land.service.truck.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import static be.kdg.sa.land.util.Calculation.calculateTruckArrivalWeight;

@Service
public class CreateFIFOTruckArrivalService {
    private final CreateRandomSellerService createRandomSellerService;
    private final TruckService truckService;
    private final DeliveryService deliveryService;
    private final TerrainService terrainService;
    private final FindWarehouseService findWarehouseService;
    private final CheckAppointmentService checkAppointmentService;
    private final Logger logger = LoggerFactory.getLogger(CreateFIFOTruckArrivalService.class);

    @Value("${endTime}")
    private int endHour;
    @Value("${startTime}")
    private int startHour;
    @Value("${defaultTruckWeight}")
    private int truckWeight;
    @Value("${occupancyMaxPercentage}")
    private double maxOccupancy;

    public CreateFIFOTruckArrivalService(CreateRandomSellerService createRandomSellerService,
                                         TruckService truckService,
                                         DeliveryService deliveryService,
                                         TerrainService terrainService,
                                         FindWarehouseService findWarehouseService,
                                         CheckAppointmentService checkAppointmentService) {
        this.createRandomSellerService = createRandomSellerService;
        this.truckService = truckService;
        this.deliveryService = deliveryService;
        this.terrainService = terrainService;
        this.findWarehouseService = findWarehouseService;
        this.checkAppointmentService = checkAppointmentService;
    }

    @Transactional
    public TruckArrival createFIFOTruckArrival(String truckLicensePlate, LocalDateTime arrivalTime, MaterialType materialType, WeighBridge weighBridge) {
        if (arrivalTime.getHour() > endHour || arrivalTime.getHour() < startHour) {
            boolean notAllowed = checkAppointmentService.checkFifoArrival(arrivalTime);
            if (notAllowed) {
                throw new AppointmentException("We are currently full for processing FIFO trucks. Come back in an hour");
            }
            Seller seller = createRandomSellerService.createSeller();

            Truck fifoTruck = truckService.findTruckByLicensePlate(truckLicensePlate).orElse(
                    truckService.create(new Truck(truckLicensePlate, new BigDecimal(truckWeight), seller))
            );

            UUID warehouseId = findWarehouseService.receiveWarehouseId(materialType.toString(), seller.getName(), seller.getAddress());
            BigDecimal warehouseOccupancyPercentage = findWarehouseService.receiveWarehouseOccupancyPercentage(warehouseId);

            if (warehouseOccupancyPercentage.doubleValue() > maxOccupancy) {
                logger.warn("   Warehouse capacity reached {}%", warehouseOccupancyPercentage);
                throw new IllegalStateException("This warehouse can't accept deliveries at the moment.");
            }

            deliveryService.create(new Delivery(materialType, fifoTruck));
            terrainService.updateTerrainByFIFOTrucks(fifoTruck);
            return new TruckArrival(fifoTruck, arrivalTime, weighBridge, calculateTruckArrivalWeight(fifoTruck.getCurrentWeight()), warehouseId);
        }
        logger.error("  You can only arrive between 20:00 and 08:00 --> your time {}:{}", arrivalTime.getHour(), arrivalTime.getMinute());
        throw new IllegalStateException("We expect you between 20:00 and 08:00 with no appointment");
    }
}
