package be.kdg.sa.land.service.ticket.pdt;

import be.kdg.sa.land.controller.dto.ticket.PdtDto;
import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.ticket.PDT;
import be.kdg.sa.land.domain.truck.Truck;
import be.kdg.sa.land.service.delivery.DeliveryService;
import be.kdg.sa.land.service.truck.TruckService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CreatePdtService {
    private final PdtService pdtService;
    private final DeliveryService deliveryService;
    private final ModelMapper modelMapper;
    private final TruckService truckService;

    public CreatePdtService(PdtService pdtService, DeliveryService deliveryService,ModelMapper modelMapper, TruckService truckService) {
        this.pdtService = pdtService;
        this.deliveryService = deliveryService;
        this.modelMapper = modelMapper;
        this.truckService = truckService;
    }

    @Transactional
    public PdtDto createPdt(PdtDto pdtDto) {
        PDT pdt = mapToObjects(pdtDto);
        addPDTToTruck(pdt, pdtDto.getDeliveryTime());

        return modelMapper.map(pdt, PdtDto.class);
    }

    private PDT mapToObjects(PdtDto pdtDto) {
        Delivery delivery = deliveryService.findDeliveryByDeliveryTime(pdtDto.getDeliveryTime());
        return pdtService.createPDT(new PDT(pdtDto.getMaterialType(), delivery.getTruck(), pdtDto.getWarehouseId(), pdtDto.getDeliveryTime()));
    }

    private void addPDTToTruck(PDT pdt, LocalDateTime deliveryTime) {
        Delivery delivery = deliveryService.findDeliveryByDeliveryTime(deliveryTime);
        Optional<Truck> optionalTruck = truckService.findTruckByLicensePlate(delivery.getTruck().getLicensePlate());
        optionalTruck.ifPresent(truck -> truck.getPdts().add(pdt));
    }
}
