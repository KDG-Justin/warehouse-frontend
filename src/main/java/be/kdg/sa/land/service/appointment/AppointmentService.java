package be.kdg.sa.land.service.appointment;

import be.kdg.sa.land.domain.*;
import be.kdg.sa.land.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Appointment> findAppointmentBySellerId(UUID sellerId) {
        logger.info("   Getting Appointment with sellerId: {}", sellerId);
        return appointmentRepository.findAppointmentBySellerId(sellerId);
    }

    @Transactional(readOnly = true)
    public Optional<Appointment> findAppointmentByTruckLicensePlate(String truckLicensePlate) {
        logger.info("   Getting Appointment with truckLicensePlate: {}", truckLicensePlate);
        return appointmentRepository.findAppointmentByTruckLicensePlate(truckLicensePlate);
    }


    @Transactional(readOnly = true)
    public Collection<Appointment> findAppointmentsWithSellers() {
        logger.info("   Getting Appointments with Sellers");
        return appointmentRepository.findAll();
    }

    @Transactional
    public Appointment create(Appointment appointment) {
        logger.info("   Creating Appointment from Seller: {}", appointment.getSeller().getName());
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void delete(Appointment appointment) {
        logger.info("   Deleting Appointment from truck: {}", appointment.getTruckLicensePlate());
        appointmentRepository.delete(appointment);
    }
}
