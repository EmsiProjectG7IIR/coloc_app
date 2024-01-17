package ma.emsi.offre_service.service;

import ma.emsi.offre_service.dao.IDao;
import ma.emsi.offre_service.entites.Offer;
import ma.emsi.offre_service.entites.User;
import ma.emsi.offre_service.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService implements IDao<Offer> {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserService userService;

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }




    @Override
    public void delete(Offer offre) {
        offerRepository.delete(offre);
    }

    @Override
    public Optional<Offer> findById(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

}
