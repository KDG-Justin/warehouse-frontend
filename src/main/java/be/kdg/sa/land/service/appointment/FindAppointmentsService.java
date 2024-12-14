package be.kdg.sa.land.service.appointment;


import be.kdg.sa.land.controller.dto.AppointmentDto;
import be.kdg.sa.land.controller.dto.truck.TruckDto;
import be.kdg.sa.land.domain.Appointment;
import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.service.delivery.DeliveryService;
import be.kdg.sa.land.service.truck.TruckService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FindAppointmentsService {
    private final AppointmentService appointmentService;
    private final TruckService truckService;
    private final DeliveryService deliveryService;
    private final ModelMapper modelMapper;


    public FindAppointmentsService(AppointmentService appointmentService, TruckService truckService, DeliveryService deliveryService, ModelMapper modelMapper) {
        this.appointmentService = appointmentService;
        this.truckService = truckService;
        this.deliveryService = deliveryService;
        this.modelMapper = modelMapper;
    }

    public Collection<AppointmentDto> findAllAppointments() {
        Collection<Appointment> appointmentCollection = appointmentService.findAppointmentsWithSellers();
        List<AppointmentDto> appointmentDtoCollection = new ArrayList<>();

        appointmentCollection.forEach(a -> {
            Truck truck = findOptionalTruck(a.getTruckLicensePlate());
            Optional<Delivery> delivery = deliveryService.findDeliveryByTruck_LicensePlate(truck.getLicensePlate());

            AppointmentDto appointmentDto = modelMapper.map(a, AppointmentDto.class);
            delivery.ifPresent(value -> appointmentDto.setMaterialType(value.getMaterialType()));
            appointmentDtoCollection.add(appointmentDto);
        });
        return appointmentDtoCollection;
    }

    public AppointmentDto findAppointmentBySellerId(UUID sellerId) {
        Appointment appointment = findOptionalAppointment(sellerId);
        AppointmentDto appointmentDto =  modelMapper.map(appointment, AppointmentDto.class);

        Truck truck = findOptionalTruck(appointment.getTruckLicensePlate());
        TruckDto truckDto = modelMapper.map(truck, TruckDto.class);
        Optional<Delivery> delivery = deliveryService.findDeliveryByTruck_LicensePlate(truckDto.getLicensePlate());

        delivery.ifPresent(value -> appointmentDto.setMaterialType(delivery.get().getMaterialType()));
        appointmentDto.setTruckLicensPlate(truckDto.getLicensePlate());

        return appointmentDto;
    }

    private Truck findOptionalTruck(String licensePlate) {
        Optional<Truck> truck = truckService.findTruckByLicensePlate(licensePlate);
        return truck.orElse(null);
    }

    private Appointment findOptionalAppointment(UUID sellerId){
        Optional<Appointment> appointment = appointmentService.findAppointmentBySellerId(sellerId);
        return appointment.orElse(null);
    }
}
