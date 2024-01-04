package ma.emsi.offre_service.repository;

import ma.emsi.offre_service.entites.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(Long id);


}
