package be.kdg.sa.land.service.seller;

import be.kdg.sa.land.domain.Seller;
import be.kdg.sa.land.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private static final Logger logger = LoggerFactory.getLogger(SellerService.class);

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Seller> findSellerByName(String name) {
        logger.info("   Finding seller {}", name);
        return sellerRepository.findSellerByName(name);
    }

    @Transactional
    public Seller create(Seller seller){
        logger.info("   Creating a new seller: {}", seller.getName());
        return sellerRepository.save(seller);
    }
}
