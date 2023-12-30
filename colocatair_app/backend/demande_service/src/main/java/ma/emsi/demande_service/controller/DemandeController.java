package ma.emsi.demande_service.controller;

import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.repository.DemandeRepository;
import ma.emsi.demande_service.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/demande")
public class DemandeController {
    @Autowired
    DemandeRepository demandeRepository;

    @Autowired
    DemandeService demandeService;

    @GetMapping("/all")
    public List chercherClients() {
        return demandeRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Demande chercherUnClients(@PathVariable Long id) throws Exception {
        return this.demandeRepository.findById(id).orElseThrow(() -> new Exception("User inexistant"));
    }

    @PostMapping("/save")
    public Demande createUser(@RequestBody Demande demande) {
        return demandeService.save(demande);
    }

}
