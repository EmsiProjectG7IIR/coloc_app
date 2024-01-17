package ma.emsi.user_service;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.repository.UserRepository;
import ma.emsi.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
//@SpringBootTest
class ServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public ServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUserTest() {
        User userToSave = new User(/* Initialize user details here */);
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        User savedUser = userService.save(userToSave);

        assertEquals(userToSave, savedUser);
        verify(userRepository, times(1)).save(userToSave);
    }

    @Test
    void deleteUserTest() {
        User userToDelete = new User(/* Initialize user details here */);

        userService.delete(userToDelete);

        verify(userRepository, times(1)).delete(userToDelete);
    }

    @Test
    void deleteByIdTest() {
        long userIdToDelete = 1L;

        userService.deleteById(userIdToDelete);

        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }

    @Test
    void findByIdTest() {
        long userId = 1L;
        User expectedUser = new User(/* Initialize user details here */);

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        Optional<User> foundUser = userService.findById(userId);

        assertEquals(Optional.of(expectedUser), foundUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findAllTest() {
        List<User> userList = new ArrayList<>();
        // Add some sample users to the list

        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUsers = userService.findAll();

        assertEquals(userList, foundUsers);
        verify(userRepository, times(1)).findAll();
    }
}
