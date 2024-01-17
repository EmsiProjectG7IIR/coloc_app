package ma.emsi.demande_service.service;


import ma.emsi.demande_service.dao.IDao;
import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import ma.emsi.demande_service.repository.DemandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DemandeService implements IDao<Demande> {



    private final UserService userService;


    private final OfferService offerService;
    private final DemandeRepository demandeRepository;




    public DemandeService(UserService userService,DemandeRepository demandeRepository, OfferService offerService) {
        this.offerService = offerService;
        this.userService = userService;

        this.demandeRepository=demandeRepository;
    }
    @Override
    public Demande save(Demande demande) {
        User user = userService.userById(demande.getDemandeurId());
        Offer offer = offerService.offerById(demande.getOfferId());


        if (user == null){

            return null;
        }else{

            demande.setDemandeur(user);
            demande.setDemandeurId(user.getId());
            demande.setOffer(offer);
            demande.setOfferId(offer.getId());
            return demandeRepository.save(demande);
        }
    }

    @Override
    public void update(Demande o) {
        demandeRepository.save(o);
    }

    @Override
    public void delete(Demande o) {
        demandeRepository.delete(o);
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