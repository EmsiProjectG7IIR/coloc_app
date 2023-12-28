package ma.emsi.user_service.service;


import ma.emsi.user_service.dao.IDao;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IDao<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User o) {

    }

    @Override
    public void delete(User id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}