package ma.emsi.offre_service.controller;

import ma.emsi.offre_service.entites.Offre;
import ma.emsi.offre_service.repository.OffreRepository;
import ma.emsi.offre_service.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/offre/")
@CrossOrigin
public class OffreController {
    @Autowired

    private OffreService offreService;

//   Offre nvOffre = new  Offre(
//                1L,
//                        1L,
//                        null,
//                        "titre2222",
//                        new Date(),  // You can replace this with the actual date
//                new Date(),  // Replace with the actual date
//                new Date(),  // Replace with the actual date
//                "description",
//                        "adresse",
//                        5000.0,
//                        true,
//                        "photo"
//                        );
    @PostMapping("/creer")
    public Offre creerOffre(@RequestBody Offre nvOffre) {

return offreService.creerOffre(nvOffre);

    }

    @GetMapping("/all")
    public List<Offre> getall() {
        // Vous pouvez ajouter la logique de validation ici
            return offreService.findAll();}

}

