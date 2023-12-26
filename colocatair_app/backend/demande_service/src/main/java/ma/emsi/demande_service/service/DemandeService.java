package ma.emsi.demande_service.service;


import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeService {

    @Autowired
    DemandeRepository demandeRepository;

    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }
}