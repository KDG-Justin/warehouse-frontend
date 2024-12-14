package be.kdg.sa.land.controller.api.truck;


import be.kdg.sa.land.service.truck.FIFOTruckProcessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fifo")
public class HandleFifoRestController {
    private final FIFOTruckProcessService fifoTruckProcessService;

    public HandleFifoRestController(FIFOTruckProcessService fifoTruckProcessService) {
        this.fifoTruckProcessService = fifoTruckProcessService;
    }

    @PostMapping("")
    public void handleFifoTruck(){
        fifoTruckProcessService.handleFifoTruck();
    }
}
