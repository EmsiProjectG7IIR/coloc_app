package ma.emsi.offre_service.repository;

import ma.emsi.offre_service.entites.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {


}
