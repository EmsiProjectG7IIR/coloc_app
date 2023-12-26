package ma.emsi.demande_service.service;

import ma.emsi.demande_service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user_service")
public interface UserService {
        @GetMapping(path = "/api/user/find/{id}")
        public User userById(@PathVariable Long id);
}