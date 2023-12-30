package ma.emsi.user_service.controller;

import ma.emsi.user_service.model.User;
import ma.emsi.user_service.repository.UserRepository;
import ma.emsi.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List chercherClients() {
        return userService.findAll();
    }

    @GetMapping("/find/{id}")
    public User chercherUnClients(@PathVariable Long id) throws Exception {
        return userService.findById(id).orElseThrow(() -> new Exception("User inexistant"));
    }

    @PostMapping("/save")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

}
