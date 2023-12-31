package ma.emsi.demande_service.controller;

import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.repository.DemandeRepository;
import ma.emsi.demande_service.service.DemandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/demande")
public class DemandeController {

    private final DemandeRepository demandeRepository;

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService,DemandeRepository demandeRepository) {
        this.demandeService = demandeService;
        this.demandeRepository=demandeRepository;
    }

    @GetMapping("/all")
    public List<Demande> chercherDemandes() {
        return demandeRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Demande chercherUnDemande(@PathVariable Long id) throws Exception {
        return this.demandeRepository.findById(id).orElseThrow(() -> new Exception("User inexistant"));
    }

    @PostMapping("/save")
    public Demande createDemande(@RequestBody Demande demande) {
        return demandeService.save(demande);
    }

}
