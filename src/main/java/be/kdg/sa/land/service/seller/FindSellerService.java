package be.kdg.sa.land.service.seller;

import be.kdg.sa.land.domain.Seller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FindSellerService {
    private final SellerService sellerService;

    public FindSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Transactional
    public Seller findOrCreateSellerByName(String sellerName, String sellerAddress) {
        Optional<Seller> seller = sellerService.findSellerByName(sellerName);
        return seller.orElseGet(() -> sellerService.create(new Seller(sellerName, sellerAddress)));
    }
}
