package be.kdg.sa.land.controller.api.truck;

import be.kdg.sa.land.controller.dto.truck.departure.CreateTruckDepartureRequest;
import be.kdg.sa.land.controller.dto.truck.departure.TruckDepartureDto;
import be.kdg.sa.land.service.truck.departure.CreateTruckDepartureService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departures")
public class DepartureRestController {
    private final CreateTruckDepartureService createTruckDepartureService;

    public DepartureRestController(CreateTruckDepartureService createTruckDepartureService) {
        this.createTruckDepartureService = createTruckDepartureService;
    }

    @PostMapping("")
    public TruckDepartureDto createTruckDeparture(@Valid @RequestBody CreateTruckDepartureRequest createTruckDepartureRequest) {
        return createTruckDepartureService.createTruckDeparture(createTruckDepartureRequest.truckLicensePlate());
    }
}
