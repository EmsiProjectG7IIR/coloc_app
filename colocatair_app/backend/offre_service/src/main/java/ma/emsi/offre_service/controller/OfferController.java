package ma.emsi.offre_service.controller;

import ma.emsi.offre_service.entites.Offer;
import ma.emsi.offre_service.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offer")
@CrossOrigin
public class OfferController {
    @Autowired
    private OfferService offerService;

    @PostMapping("/save")
    public Offer saveOffre(@RequestBody Offer offer) {
        return offerService.save(offer);
    }

    @GetMapping("/all")
    public List<Offer> findAll() {
        return offerService.findAll();
    }

    @GetMapping("/find/{id}")
    public Offer findOffreById(@PathVariable Long id) throws Exception {
        return offerService.findById(id).orElseThrow(() -> new Exception("User inexistant"));
    }
}

