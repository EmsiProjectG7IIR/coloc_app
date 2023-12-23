package ma.emsi.user_service.Controller;

import ma.emsi.user_service.Model.User;
import ma.emsi.user_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List chercherClients() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User chercherUnClients(@PathVariable Long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("User inexistant"));
    }

}
