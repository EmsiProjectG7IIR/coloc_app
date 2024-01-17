package ma.emsi.user_service;

import ma.emsi.user_service.controller.UserController;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;
    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }
    @Test
    void testFindAll() {
        User user1 = new User(1L, "1001L", "John", "Doe", "john.doe@example.com", new Date());
        User user2 = new User(2L, "1002L", "Jane", "Doe", "jane.doe@example.com", new Date());
        List<User> mockUsers = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(mockUsers);

        List<User> result = userController.chercherClients();



        assertEquals(2, result.size());
        verify(userService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        long userId = 1L;
        User mockUser = new User(userId, "1001L", "John", "Doe", "john.doe@example.com", new Date());

        when(userService.findById(userId)).thenReturn(Optional.of(mockUser));

        Optional<User> result = userController.chercherUnClients(userId);

        assertEquals(userId, result.get().getId());
        verify(userService, times(1)).findById(userId);
    }
}
