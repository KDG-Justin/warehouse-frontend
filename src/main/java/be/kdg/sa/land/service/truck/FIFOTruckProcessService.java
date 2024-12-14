package be.kdg.sa.land.service.truck;


import be.kdg.sa.land.controller.dto.terrain.TerrainDto;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.service.TerrainService;
import be.kdg.sa.land.service.amqp.SendDeliveryToWarehouseService;
import be.kdg.sa.land.service.truck.departure.CreateTruckDepartureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FIFOTruckProcessService {
    private final TerrainService terrainService;
    private final SendDeliveryToWarehouseService sendDeliveryToWarehouseService;
    private final CreateTruckDepartureService createTruckDepartureService;
    private final Logger logger = LoggerFactory.getLogger(FIFOTruckProcessService.class);

    @Value("${startTime}")
    private int startTime;
    @Value("${endTime}")
    private int endTime;


    public FIFOTruckProcessService(TerrainService terrainService,
                                   SendDeliveryToWarehouseService sendDeliveryToWarehouseService,
                                   CreateTruckDepartureService createTruckDepartureService) {
        this.terrainService = terrainService;
        this.sendDeliveryToWarehouseService = sendDeliveryToWarehouseService;
        this.createTruckDepartureService = createTruckDepartureService;
    }

    @Transactional
    public void handleFifoTruck(){
        if (LocalDateTime.now().getHour() >= endTime || LocalDateTime.now().getHour() <= startTime){
            TerrainDto terrain = terrainService.findTerrain("Main-Terrain");
            Optional<Truck> fifoTruck = terrain.getFifoTrucks().stream().findFirst();

            if (fifoTruck.isEmpty()){
                throw new NullPointerException("There are no fifo trucks to handle.");
            }

            logger.info("   Handling FIFO Truck {}", fifoTruck.get().getLicensePlate());
            sendDeliveryToWarehouseService.sendFifoDeliveryToWarehouse(fifoTruck.get().getLicensePlate());
            createTruckDepartureService.createTruckDeparture(fifoTruck.get().getLicensePlate());

        }
    }
}
