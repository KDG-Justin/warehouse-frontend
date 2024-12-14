package be.kdg.sa.land.controller.api.truck;


import be.kdg.sa.land.controller.dto.truck.arrival.CreateTruckArrivalRequest;
import be.kdg.sa.land.controller.dto.truck.arrival.TruckArrivalDto;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.service.truck.arrival.CreateTruckArrivalService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/arrivals")
public class ArrivalRestController {
    private final CreateTruckArrivalService createTruckArrivalService;
    private final ModelMapper modelMapper;

    public ArrivalRestController(CreateTruckArrivalService createTruckArrivalService, ModelMapper modelMapper) {
        this.createTruckArrivalService = createTruckArrivalService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("")
    public TruckArrivalDto createTruckArrival(@Valid @RequestBody CreateTruckArrivalRequest createTruckArrivalRequest) {
        TruckArrival truckArrival = createTruckArrivalService.createTruckArrival(createTruckArrivalRequest.truckLicensePlate(),
                createTruckArrivalRequest.arrivalTime(), createTruckArrivalRequest.materialType());
        return modelMapper.map(truckArrival, TruckArrivalDto.class);
    }




}
