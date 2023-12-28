package ma.emsi.demande_service.service;

import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="SERVICE-OFFER")
public interface OfferService {
    @GetMapping(path = "/api/offer/find/{id}")
    Offer offerById (@PathVariable("id") Long id);
}
