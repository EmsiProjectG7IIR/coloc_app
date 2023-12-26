package ma.emsi.offre_service.service;

import ma.emsi.offre_service.entites.Offre;
import ma.emsi.offre_service.entites.User;
import ma.emsi.offre_service.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    private UserService userService;

    public Offre creerOffre(Offre nouvelleOffre) {
//      Optional<User> user = userService.userById(nouvelleOffre.getUser().getId());
//        if (user.isEmpty()) {
//            throw new RuntimeException("Category not found.");
//        }
        nouvelleOffre.setUser(new User());
        return offreRepository.save(nouvelleOffre);
    }

    public List<Offre> findAll() {
        return offreRepository.findAll();
    }
}
