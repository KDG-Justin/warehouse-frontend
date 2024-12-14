package be.kdg.sa.land.service.appointment;

import be.kdg.sa.land.controller.dto.AppointmentDto;
import be.kdg.sa.land.controller.dto.SellerDto;
import be.kdg.sa.land.domain.Appointment;
import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.Seller;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.exception.AppointmentException;
import be.kdg.sa.land.service.delivery.DeliveryService;
import be.kdg.sa.land.service.amqp.SendSellerToWarehouseService;
import be.kdg.sa.land.service.seller.FindSellerService;
import be.kdg.sa.land.service.truck.TruckService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CreateAppointmentService {
    private final AppointmentService appointmentService;
    private final TruckService truckService;
    private final DeliveryService deliveryService;
    private final FindSellerService findSellerService;
    private final SendSellerToWarehouseService sendSellerToWarehouseService;
    private final CheckAppointmentService checkAppointmentService;
    @Value("${defaultTruckWeight}")
    private int truckWeight;

    public CreateAppointmentService(AppointmentService appointmentService,
                                    TruckService truckService,
                                    DeliveryService deliveryService,
                                    FindSellerService findSellerService,
                                    SendSellerToWarehouseService sendSellerToWarehouseService,
                                    CheckAppointmentService checkAppointmentService) {
        this.appointmentService = appointmentService;
        this.truckService = truckService;
        this.deliveryService = deliveryService;
        this.findSellerService = findSellerService;
        this.sendSellerToWarehouseService = sendSellerToWarehouseService;
        this.checkAppointmentService = checkAppointmentService;
    }

    @Transactional
    public void createAppointment(AppointmentDto appointmentDto) {
        boolean notAllowed = checkAppointmentService.checkAppointment(appointmentDto.getArrivalTime());
        if (notAllowed) {
            throw new AppointmentException(String.format("Arrival time %d-%d-%d %d:00 is full. Choose another Arrival Time", appointmentDto.getArrivalTime().getYear(), appointmentDto.getArrivalTime().getMonthValue(), appointmentDto.getArrivalTime().getDayOfMonth(), appointmentDto.getArrivalTime().getHour()));
        } else {
            Seller seller = findSellerService.findOrCreateSellerByName(appointmentDto.getSellerName(), appointmentDto.getSellerAddress());
            Truck truck = truckService.findTruckByLicensePlate(appointmentDto.getTruckLicensPlate()).orElse(
                    truckService.create(new Truck(appointmentDto.getTruckLicensPlate(), new BigDecimal(truckWeight), seller))
            );
            deliveryService.create(new Delivery(appointmentDto.getMaterialType(), truck));
            appointmentService.create(new Appointment(appointmentDto.getArrivalTime(), seller, truck.getLicensePlate()));

            sendSellerToWarehouseService.sendSellerToWarehouse(new SellerDto(seller.getName(), seller.getAddress()));
        }
    }
}
