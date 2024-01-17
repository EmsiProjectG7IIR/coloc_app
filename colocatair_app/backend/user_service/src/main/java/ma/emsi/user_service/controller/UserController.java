package ma.emsi.user_service.controller;

import ma.emsi.user_service.model.User;
import ma.emsi.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public List<User> chercherClients() {
        return userService.findAll();
    }
    @GetMapping("/find/{id}")
    public Optional<User> chercherUnClients(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }


    @GetMapping("/findByUid/{uid}")
    public User chercherUnUser(@PathVariable String uid) {
        return userService.findByUid(uid);
    }
}
