package be.kdg.sa.land.service.truck.arrival;

import be.kdg.sa.land.controller.dto.AppointmentDto;
import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.WeighBridge;
import be.kdg.sa.land.domain.enums.MaterialType;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.service.delivery.DeliveryService;
import be.kdg.sa.land.service.TerrainService;
import be.kdg.sa.land.service.WeighBridgeService;
import be.kdg.sa.land.service.appointment.FindAppointmentsService;
import be.kdg.sa.land.service.rest.FindWarehouseService;
import be.kdg.sa.land.service.truck.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static be.kdg.sa.land.util.Calculation.calculateTruckArrivalWeight;
import static be.kdg.sa.land.util.Functions.checkArrivalTime;
import static be.kdg.sa.land.util.Functions.formatStringToLocalDateTime;

@Service
public class CreateTruckArrivalService {
    private final TruckService truckService;
    private final TruckArrivalService truckArrivalService;
    private final WeighBridgeService weighBridgeService;
    private final TerrainService terrainService;
    private final DeliveryService deliveryService;
    private final CreateFIFOTruckArrivalService createFIFOTruckArrivalService;
    private final FindAppointmentsService findAppointmentsService;
    private final FindWarehouseService findWarehouseService;
    private final Logger logger = LoggerFactory.getLogger(CreateTruckArrivalService.class);
    @Value("${weighbridgeIn}")
    private int weighBridgeIn;
    @Value("${occupancyMaxPercentage}")
    private double maxOccupancy;


    public CreateTruckArrivalService(TruckService truckService,
                                     TruckArrivalService truckArrivalService,
                                     WeighBridgeService weighBridgeService,
                                     TerrainService terrainService,
                                     DeliveryService deliveryService,
                                     CreateFIFOTruckArrivalService createFIFOTruckArrivalService,
                                     FindAppointmentsService findAppointmentsService,
                                     FindWarehouseService findWarehouseService) {
        this.truckService = truckService;
        this.truckArrivalService = truckArrivalService;
        this.weighBridgeService = weighBridgeService;
        this.terrainService = terrainService;
        this.deliveryService = deliveryService;
        this.createFIFOTruckArrivalService = createFIFOTruckArrivalService;
        this.findAppointmentsService = findAppointmentsService;
        this.findWarehouseService = findWarehouseService;

    }

    @Transactional
    public TruckArrival createTruckArrival(String truckLicensePlate, String arrivalTime, MaterialType materialType) {
        TruckArrival truckArrival = mapToObjects(truckLicensePlate, arrivalTime, materialType);
        return truckArrivalService.createArrival(truckArrival);

    }


    private TruckArrival mapToObjects(String truckLicensePlate, String arrivalTime, MaterialType materialType) {
        Optional<Truck> truck = truckService.findTruckByLicensePlate(truckLicensePlate);
        WeighBridge weighBridge = weighBridgeService.findWeighBridge(weighBridgeIn);
        LocalDateTime localDateTime = formatStringToLocalDateTime(arrivalTime);
        Optional<Delivery> delivery = deliveryService.findDeliveryByTruck_LicensePlate(truckLicensePlate);

        if (truck.isEmpty() || delivery.isEmpty()){
            return createFIFOTruckArrivalService.createFIFOTruckArrival(truckLicensePlate, localDateTime, materialType, weighBridge);
        }


        AppointmentDto appointment = findAppointmentsService.findAppointmentBySellerId(truck.get().getSeller().getId());
        String arrivalType = checkArrivalTime(localDateTime, appointment.getArrivalTime());
        MaterialType chosenMaterialType = delivery.get().getMaterialType();
        UUID warehouseUUID = findWarehouseService.receiveWarehouseId(chosenMaterialType.toString(), truck.get().getSeller().getName(), truck.get().getSeller().getAddress());

        BigDecimal warehouseOccupancyPercentage = findWarehouseService.receiveWarehouseOccupancyPercentage(warehouseUUID);

        if (warehouseOccupancyPercentage.doubleValue() > maxOccupancy) {
            logger.warn("   Warehouse capacity reached {}%", warehouseOccupancyPercentage);
            throw new IllegalStateException("This warehouse can't accept deliveries at the moment.");
        }

        return switch (arrivalType) {
            case "early" -> {
                logger.warn("   You are EARLY. We expect you at {}:00 :|", appointment.getArrivalTime().getHour());
                throw new IllegalArgumentException("You are EARLY");
            }
            case "late" -> {
                logger.warn("   You are LATE. We are putting you in FIFO row :(");
                terrainService.updateTerrainByFIFOTrucks(truck.get());

                yield new TruckArrival(truck.get(), localDateTime, weighBridge, calculateTruckArrivalWeight(truck.get().getCurrentWeight()), warehouseUUID);
            }
            case "onTime" -> {
                logger.info("   You are ONTIME. We have been expecting you :)");
                terrainService.updateTerrainByTrucks(truck.get());
                yield new TruckArrival(truck.get(), localDateTime, weighBridge, calculateTruckArrivalWeight(truck.get().getCurrentWeight()), warehouseUUID);
            }
            default -> throw new IllegalStateException("Unexpected value: " + arrivalType);
        };
    }
}