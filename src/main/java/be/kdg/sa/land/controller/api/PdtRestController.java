package be.kdg.sa.land.controller.api;

import be.kdg.sa.land.controller.dto.ticket.PdtDto;
import be.kdg.sa.land.service.ticket.pdt.CreatePdtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdt")
public class PdtRestController {
    private final CreatePdtService createPdtService;

    public PdtRestController(CreatePdtService createPdtService) {
        this.createPdtService = createPdtService;
    }

    @PostMapping("")
    public PdtDto createPdt(@RequestBody PdtDto pdtDto) {
        return createPdtService.createPdt(pdtDto);
    }
}
