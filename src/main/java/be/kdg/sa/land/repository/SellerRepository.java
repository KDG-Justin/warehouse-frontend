package be.kdg.sa.land.repository;

import be.kdg.sa.land.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {

    Optional<Seller> findSellerByName(String name);
}
