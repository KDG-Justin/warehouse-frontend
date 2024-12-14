package be.kdg.sa.land.service.appointment;

import be.kdg.sa.land.controller.dto.AppointmentDto;
import be.kdg.sa.land.controller.dto.terrain.TerrainDto;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.service.TerrainService;
import be.kdg.sa.land.service.truck.arrival.TruckArrivalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static be.kdg.sa.land.util.Functions.filterAppointmentDtoS;
import static be.kdg.sa.land.util.Functions.filterFifoTruckArrivals;

@Service
public class CheckAppointmentService {
    private final FindAppointmentsService findAppointmentsService;
    private final TerrainService terrainService;
    private final TruckArrivalService truckArrivalService;
    private final Logger logger = LoggerFactory.getLogger(CheckAppointmentService.class);

    @Value("${maxTrucks}")
    private int maxTrucks;

    @Value("${maxFIFOTrucks}")
    private int maxFifoTrucks;

    public CheckAppointmentService(FindAppointmentsService findAppointmentsService,
                                   TerrainService terrainService,
                                   TruckArrivalService truckArrivalService) {
        this.findAppointmentsService = findAppointmentsService;
        this.terrainService = terrainService;
        this.truckArrivalService = truckArrivalService;
    }

    @Transactional(readOnly = true)
    public boolean checkAppointment(LocalDateTime truckArrivalTime){
        logger.info("   Checking if Appointment is possible.");
        Collection<AppointmentDto> dtoList = findAppointmentsService.findAllAppointments();
        List<AppointmentDto> filteredDtoList = filterAppointmentDtoS(dtoList, truckArrivalTime);

        return filteredDtoList.size() == maxTrucks;

    }

    @Transactional(readOnly = true)
    public boolean checkFifoArrival(LocalDateTime fifoTruckArrivalTime){
        logger.info("   Checking if FIFO Arrival is possible.");
        TerrainDto terrain = terrainService.findTerrain("Main-Terrain");
        List<TruckArrival> fifoTruckArrivals = new ArrayList<>();

        terrain.getFifoTrucks().forEach(truck -> {
            fifoTruckArrivals.add(truckArrivalService.findTruckArrivalByTruckLicensePlate(truck.getLicensePlate()));
        });

        List<TruckArrival> filteredFifoTruckArrivals = filterFifoTruckArrivals(fifoTruckArrivals, fifoTruckArrivalTime);
        return filteredFifoTruckArrivals.size() == maxFifoTrucks;
    }

}
