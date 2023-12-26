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

    @PostMapping("/add")
    public Offer creerOffre(@RequestBody Offer offer) {
        return offerService.addOffer(offer);
    }

    @GetMapping("/all")
    public List<Offer> getall() {
        return offerService.findAll();
    }
}

