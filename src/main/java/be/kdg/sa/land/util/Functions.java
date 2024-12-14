package be.kdg.sa.land.util;

import be.kdg.sa.land.controller.dto.AppointmentDto;
import be.kdg.sa.land.domain.truck.TruckArrival;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class Functions {

    public static LocalDateTime formatStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }


    public static String checkArrivalTime(LocalDateTime arrivalTime, LocalDateTime appointmentTime) {
        if (arrivalTime.getHour() < appointmentTime.getHour()) return "early";
        if (arrivalTime.getHour() > appointmentTime.getHour()) return "late";
        return "onTime";
    }

    public static List<AppointmentDto> filterAppointmentDtoS(Collection<AppointmentDto> appointments, LocalDateTime truckArrivalTime) {

        return appointments.stream()
                .filter(appointment ->
                        appointment.getArrivalTime().getYear() == truckArrivalTime.getYear() &&
                                appointment.getArrivalTime().getMonth() == truckArrivalTime.getMonth() &&
                                appointment.getArrivalTime().getDayOfMonth() == truckArrivalTime.getDayOfMonth() &&
                                appointment.getArrivalTime().getHour() == truckArrivalTime.getHour()
                )
                .toList();
    }

    public static List<TruckArrival> filterFifoTruckArrivals(List<TruckArrival> truckArrivals, LocalDateTime fifoTruckArrivalTime) {
        return truckArrivals.stream()
                .filter(arrival ->
                        arrival.getArrivalTime().getYear() == fifoTruckArrivalTime.getYear() &&
                                arrival.getArrivalTime().getMonth() == fifoTruckArrivalTime.getMonth() &&
                                arrival.getArrivalTime().getDayOfMonth() == fifoTruckArrivalTime.getDayOfMonth() &&
                                arrival.getArrivalTime().getHour() == fifoTruckArrivalTime.getHour()
                )
                .toList();
    }
}
