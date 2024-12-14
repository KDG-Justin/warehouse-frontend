package be.kdg.sa.land.domain.customs;

import be.kdg.sa.land.util.TimeRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface AppointmentTimeRange {
    String message() default "Arrival time must be between 08:00 and 20:00";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}