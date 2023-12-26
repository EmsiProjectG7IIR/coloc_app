package ma.emsi.offre_service.service;

import ma.emsi.offre_service.entites.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="SERVICE-USER")
public  interface UserService {

        @GetMapping(path = "/user/{id}")
        Optional<User> userById (@PathVariable Long id);









}