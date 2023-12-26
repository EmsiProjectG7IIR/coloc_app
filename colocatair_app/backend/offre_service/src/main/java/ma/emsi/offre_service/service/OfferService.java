package ma.emsi.offre_service.service;

import ma.emsi.offre_service.entites.Offer;
import ma.emsi.offre_service.entites.User;
import ma.emsi.offre_service.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService{
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserService userService;

    public Offer addOffer(Offer offer) {
//      Optional<User> user = userService.userById(nouvelleOffre.getUser().getId());
//        if (user.isEmpty()) {
//            throw new RuntimeException("Category not found.");
//        }
        System.out.println("hhhhhhhhhhhhh "+ offer.getId_createur());
        User user = userService.userById(offer.getId_createur());

        if (user == null){
            System.out.println("User not found!!");
            return null;
        }else{
            System.out.println("test : "+user);
            offer.setUser(user);
            offer.setId_createur(user.getId());
            return offerRepository.save(offer);
        }
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

//    @Override
//    public StandardResponse<Offer> addOffer(Integer authorId, Integer postId, String body, HttpServletRequest request) {
//        //System.out.println("hhhhhhhhhhhhh "+ offer.getId_createur());
//        StandardResponse<User> user = userService.userById(offer.getId_createur());
//        // User user = userService.userById(offer.getId_createur());
//        if(user.getData()==null){
//            return new StandardResponse<>(HttpStatus.NOT_FOUND.value(), "Get User by userId dont success "+ user.getMessage(),
//                    request.getRequestURI(), null, null, null);
//        }
//
//        if (user == null){
//            System.out.println("User not found!!");
//            return null;
//        }else{
//            System.out.println("test : "+user);
//            offer.setUser(user);
//            offer.setId_createur(user.getId());
//            return offerRepository.save(offer);
//        }
//    }
}
