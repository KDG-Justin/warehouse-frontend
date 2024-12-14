package be.kdg.sa.land.util;

import be.kdg.sa.land.domain.customs.AppointmentTimeRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public class TimeRangeValidator implements ConstraintValidator<AppointmentTimeRange, LocalDateTime> {
    @Value("${startTime}")
    private int startTime;
    @Value("${endTime}")
    private int endTime;

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        int hour = localDateTime.getHour();
        return hour >= startTime && hour <= endTime;
    }
}
