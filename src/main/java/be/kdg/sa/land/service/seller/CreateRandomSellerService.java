package be.kdg.sa.land.service.seller;

import be.kdg.sa.land.controller.dto.SellerDto;
import be.kdg.sa.land.domain.Seller;
import be.kdg.sa.land.service.amqp.SendSellerToWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static be.kdg.sa.land.util.RandomSellerCreation.createRandomSeller;

@Service
public class CreateRandomSellerService {
    private final FindSellerService findSellerService;
    private final SendSellerToWarehouseService sendSellerToWarehouseService;

    public CreateRandomSellerService(FindSellerService findSellerService, SendSellerToWarehouseService sendSellerToWarehouseService) {
        this.findSellerService = findSellerService;
        this.sendSellerToWarehouseService = sendSellerToWarehouseService;
    }

    @Transactional
    public Seller createSeller() {
        Seller randomSeller = createRandomSeller();
        sendSellerToWarehouseService.sendSellerToWarehouse(new SellerDto(randomSeller.getName(), randomSeller.getAddress()));
        return findSellerService.findOrCreateSellerByName(randomSeller.getName(), randomSeller.getAddress());
    }
}
