package be.kdg.sa.land.repository;

import be.kdg.sa.land.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    Optional<Appointment> findAppointmentBySellerId(UUID sellerId);
    Optional<Appointment> findAppointmentByTruckLicensePlate(String truckLicensePlate);
}
