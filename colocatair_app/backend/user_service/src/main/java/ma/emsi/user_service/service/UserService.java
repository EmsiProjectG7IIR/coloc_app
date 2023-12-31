package ma.emsi.user_service.service;


import ma.emsi.user_service.dao.IDao;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IDao<User> {


   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User o) {
        throw new UnsupportedOperationException("cannot be performed right now");

    }

    @Override
    public void delete(User o) {
        throw new UnsupportedOperationException("cannot be performed right now");
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}