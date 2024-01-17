package ma.emsi.user_service.service;


import ma.emsi.user_service.model.User;
import ma.emsi.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{


   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User o) {
        userRepository.delete(o);
    }
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}