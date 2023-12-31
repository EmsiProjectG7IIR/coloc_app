package ma.emsi.user_service;

import ma.emsi.user_service.controller.UserController;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.repository.UserRepository;
import ma.emsi.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserServiceApplicationTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;



    @Test
    void shouldReturnListOfUsers()  {
        // Mocking the UserService behavior
        when(userService.findAll()).thenReturn(Arrays.asList(
                new User(null, 123L, "John", "Doe", "john.doe@example.com", new Date()),
                new User(null, 123L, "Jane", "Doe", "john.doe@example.com", new Date())
        ));

        List<User> userList = userController.chercherClients();

        // Assertions
        assertEquals(2, userList.size());
        assertEquals("John", userList.get(0).getNom());
        assertEquals("Jane", userList.get(1).getNom());
    }

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() {
        // Create a user to be saved
        User newUser = new User(null, 123L, "John", "Doe", "john.doe@example.com", new Date());

        // Mocking the UserRepository behavior
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // Call the method in the service
        User savedUser = userService.save(newUser);

        // Assertions
        assertEquals(123L, savedUser.getUId());
        assertEquals("John", savedUser.getNom());
        assertEquals("Doe", savedUser.getPrenom());
        assertEquals("john.doe@example.com", savedUser.getEmail());
        // Add more assertions based on your User model
    }
//    @Test
//    void shouldCreateUser() {
//        // Create a user to be sent in the request
//        User newUser = new User(null, 123L, "John", "Doe", "john.doe@example.com", new Date());
//
//        // Mocking the UserService behavior
//        when(userService.save(any(User.class))).thenReturn(newUser);
//
//        // Call the method in the controller
//        User createdUser = userController.createUser(newUser);
//
//        // Assertions
//        assertEquals(123L, createdUser.getUId());
//        assertEquals("John", createdUser.getNom());
//        assertEquals("Doe", createdUser.getPrenom());
//        assertEquals("john.doe@example.com", createdUser.getEmail());
//        // Add more assertions based on your User model
//    }


    @Test
    void shouldReturnUserById() throws Exception {
        // Mocking the UserService behavior
        when(userService.findById(1L)).thenReturn(Optional.of(new User(1L, 123L, "John", "Doe", "john.doe@example.com", new Date())));

        // Call the method in the controller
        User user = userController.chercherUnClients(1L);

        // Assertions
        assertEquals(1L, user.getId());
        assertEquals(123L, user.getUId());
        assertEquals("John", user.getNom());
        assertEquals("Doe", user.getPrenom());
        assertEquals("john.doe@example.com", user.getEmail());

        // Test for non-existing user
        when(userService.findById(2L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> userController.chercherUnClients(2L));
    }
    @Test
    void contextLoads() {
        assertNotNull(userService);
    assertNotNull(userController);}



}
