package be.kdg.sa.land.service.delivery;

import be.kdg.sa.land.controller.dto.truck.DeliveryDto;
import be.kdg.sa.land.domain.Delivery;
import be.kdg.sa.land.domain.truck.TruckArrival;
import be.kdg.sa.land.service.truck.arrival.TruckArrivalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FindDeliveryService {
    private final DeliveryService deliveryService;
    private final TruckArrivalService truckArrivalService;
    private final ModelMapper modelMapper;
    @Value("${deliveryTime}")
    private int deliveryTime;

    public FindDeliveryService(DeliveryService deliveryService, TruckArrivalService truckArrivalService,ModelMapper modelMapper) {
        this.deliveryService = deliveryService;
        this.truckArrivalService = truckArrivalService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public DeliveryDto findDeliveryByTruckLicensePlate(String licensePlate) {
        //Stap 2 van schema
        Delivery delivery = modelToObject(licensePlate);
        return modelMapper.map(delivery, DeliveryDto.class);
    }

    private Delivery modelToObject(String licensePlate){
        Optional<Delivery> optionalDelivery = deliveryService.findDeliveryByTruck_LicensePlate(licensePlate);
        TruckArrival truckArrival = truckArrivalService.findTruckArrivalByTruckLicensePlate(licensePlate);

        if (optionalDelivery.isPresent()){
            Delivery delivery = optionalDelivery.get();
            delivery.setWarehouseId(truckArrival.getWarehouse());
            delivery.setDeliveryTime(truckArrival.getArrivalTime().plusMinutes(deliveryTime));
            return delivery;
        }

        return null;
    }

}
