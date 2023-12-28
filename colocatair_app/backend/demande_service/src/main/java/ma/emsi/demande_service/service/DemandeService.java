package ma.emsi.demande_service.service;


import ma.emsi.demande_service.dao.IDao;
import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import ma.emsi.demande_service.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService implements IDao<Demande> {

    @Autowired
    DemandeRepository demandeRepository;

    @Autowired
    UserService userService;

    @Autowired
    OfferService offerService;

    @Override
    public Demande save(Demande demande) {
        User user = userService.userById(demande.getDemandeur_id());
        Offer offer = offerService.offerById(demande.getOffer_id());

        if (user == null){
            System.out.println("User not found!!");
            return null;
        }else{
            System.out.println("test : "+user);
            demande.setDemandeur(user);
            demande.setDemandeur_id(user.getId());
            demande.setOffer(offer);
            demande.setOffer_id(offer.getId());
            return demandeRepository.save(demande);
        }
    }

    @Override
    public void update(Demande o) {

    }

    @Override
    public void delete(Demande o) {

    }

    @Override
    public Optional<Demande> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Demande> findAll() {
        return demandeRepository.findAll();
    }
}