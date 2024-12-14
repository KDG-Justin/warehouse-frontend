package be.kdg.sa.land.service.truck.departure;

import be.kdg.sa.land.controller.dto.truck.DeliveryDto;
import be.kdg.sa.land.controller.dto.truck.departure.TruckDepartureDto;
import be.kdg.sa.land.domain.Appointment;
import be.kdg.sa.land.domain.WeighBridge;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.domain.truck.TruckDeparture;
import be.kdg.sa.land.service.TerrainService;
import be.kdg.sa.land.service.WeighBridgeService;
import be.kdg.sa.land.service.appointment.AppointmentService;
import be.kdg.sa.land.service.delivery.FindDeliveryService;
import be.kdg.sa.land.service.ticket.wbt.CreateWbtService;
import be.kdg.sa.land.service.truck.arrival.TruckArrivalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static be.kdg.sa.land.util.Calculation.calculateTruckDepartureWeight;

@Service
public class CreateTruckDepartureService {
    private final TruckArrivalService truckArrivalService;
    private final TerrainService terrainService;
    private final WeighBridgeService weighBridgeService;
    private final TruckDepartureService truckDepartureService;
    private final ModelMapper modelMapper;
    private final FindDeliveryService findDeliveryService;
    private final CreateWbtService createWbtService;
    private final AppointmentService appointmentService;

    @Value("${weighbridgeOut}")
    private int weighbridgeOut;
    @Value("${departureTime}")
    private int departureTime;


    public CreateTruckDepartureService(TruckArrivalService truckArrivalService ,
                                       TerrainService terrainService,
                                       WeighBridgeService weighBridgeService ,
                                       TruckDepartureService truckDepartureService,
                                       ModelMapper modelMapper,
                                       FindDeliveryService findDeliveryService,
                                       CreateWbtService createWbtService,
                                       AppointmentService appointmentService) {
        this.truckArrivalService = truckArrivalService;
        this.terrainService = terrainService;
        this.weighBridgeService = weighBridgeService;
        this.truckDepartureService = truckDepartureService;
        this.modelMapper = modelMapper;
        this.findDeliveryService = findDeliveryService;
        this.createWbtService = createWbtService;
        this.appointmentService = appointmentService;
    }

    @Transactional
    public TruckDepartureDto createTruckDeparture(String truckLicensePlate) {
        TruckDeparture truckDeparture = mapToObjects(truckLicensePlate);
        LocalDateTime deliveryTime = truckDeparture.getDepartureTime().minusMinutes(departureTime);

        createWbtService.createWBT(truckLicensePlate, deliveryTime);
        return modelMapper.map(truckDeparture, TruckDepartureDto.class);
    }

    private TruckDeparture mapToObjects(String licensePlate) {
        TruckArrival truckArrival = truckArrivalService.findTruckArrivalByTruckLicensePlate(licensePlate);
        WeighBridge weighBridge = weighBridgeService.findWeighBridge(weighbridgeOut);
        BigDecimal truckWeight = calculateTruckDepartureWeight(truckArrival.getCurrentWeight());
        DeliveryDto delivery = findDeliveryService.findDeliveryByTruckLicensePlate(licensePlate);

        deleteAppointment(licensePlate);
        deleteTruckFromTerrain(truckArrival.getTruck());

        return truckDepartureService.createTruckDeparture(new TruckDeparture(truckArrival.getTruck(), weighBridge, delivery.getDeliveryTime().plusMinutes(departureTime), truckWeight));
    }

    private void deleteTruckFromTerrain(Truck truck) {
        terrainService.deleteTruckFromTerrain(truck);
        terrainService.deleteTruckFromFIFOTerrain(truck);
    }

    private void deleteAppointment(String licensePlate) {
        Optional<Appointment> appointment = appointmentService.findAppointmentByTruckLicensePlate(licensePlate);
        appointment.ifPresent(appointmentService::delete);

    }

}
