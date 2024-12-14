package be.kdg.sa.land.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AppointmentException extends IllegalStateException {

    public AppointmentException(String description) {
        super(description);
    }
}
