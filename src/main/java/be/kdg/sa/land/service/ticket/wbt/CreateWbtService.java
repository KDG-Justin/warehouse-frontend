package be.kdg.sa.land.service.ticket.wbt;

import be.kdg.sa.land.domain.ticket.WBT;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.domain.truck.TruckDeparture;
import be.kdg.sa.land.service.amqp.SendMaterialAmountToWarehouseService;
import be.kdg.sa.land.service.truck.TruckService;
import be.kdg.sa.land.service.truck.arrival.TruckArrivalService;
import be.kdg.sa.land.service.truck.departure.TruckDepartureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static be.kdg.sa.land.util.Calculation.calculateMaterialWeight;

@Service
public class CreateWbtService {
    private final WbtService wbtService;
    private final TruckArrivalService truckArrivalService;
    private final TruckDepartureService truckDepartureService;
    private final TruckService truckService;
    private final SendMaterialAmountToWarehouseService sendMaterialAmountToWarehouseService;
    private final Logger logger = LoggerFactory.getLogger(CreateWbtService.class);

    @Value("${endTime}")
    private int endHour;
    @Value("${startTime}")
    private int startHour;

    public CreateWbtService(WbtService wbtService,
                            TruckArrivalService truckArrivalService,
                            TruckDepartureService truckDepartureService,
                            TruckService truckService,
                            SendMaterialAmountToWarehouseService sendMaterialAmountToWarehouseService) {
        this.wbtService = wbtService;
        this.truckArrivalService = truckArrivalService;
        this.truckDepartureService = truckDepartureService;
        this.truckService = truckService;
        this.sendMaterialAmountToWarehouseService = sendMaterialAmountToWarehouseService;
    }

    @Transactional
    public void createWBT(String licensePlate, LocalDateTime deliveryTime){
        logger.info("   Creating WBT for truck: {}", licensePlate);
        WBT wbt = wbtService.createWBT(mapToObjects(licensePlate));
        addWBTToTruck(wbt, licensePlate);

        if (deliveryTime.getHour() > endHour || deliveryTime.getHour() < startHour){
            sendMaterialAmountToWarehouseService.sendFifoMaterialAmountToWarehouse(licensePlate, deliveryTime);
        } else {
            sendMaterialAmountToWarehouseService.sendMaterialAmountToWarehouse(licensePlate, deliveryTime);
        }
    }

    private WBT mapToObjects(String licensePlate){
        TruckArrival truckArrival = truckArrivalService.findTruckArrivalByTruckLicensePlate(licensePlate);
        TruckDeparture truckDeparture = truckDepartureService.findTruckDepartureByTruckLicensePlate(licensePlate);
        BigDecimal materialWeight = calculateMaterialWeight(truckDeparture.getCurrentWeight(), truckArrival.getCurrentWeight());
        return new WBT(truckDeparture.getTruck(), truckArrival.getCurrentWeight(), materialWeight, truckDeparture.getCurrentWeight(), truckDeparture.getDepartureTime());
    }

    private void addWBTToTruck(WBT wbt, String licensePlate){
        Optional<Truck> truck = truckService.findTruckByLicensePlate(licensePlate);
        truck.ifPresent(value -> value.getWbts().add(wbt));
    }
}
