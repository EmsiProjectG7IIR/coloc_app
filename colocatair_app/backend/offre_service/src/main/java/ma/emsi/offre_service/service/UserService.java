package ma.emsi.offre_service.service;

import ma.emsi.offre_service.entites.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="SERVICE-USER")
public interface UserService {
        @GetMapping(path = "/api/user/find/{id}")
        User userById (@PathVariable("id") Long id);

        @GetMapping(path = "/api/user/findByUid/{uid}")
        User userByUid (@PathVariable("uid") String uid);
}