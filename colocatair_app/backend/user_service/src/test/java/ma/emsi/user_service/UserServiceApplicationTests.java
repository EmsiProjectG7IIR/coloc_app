package ma.emsi.user_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.emsi.user_service.controller.UserController;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserServiceApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    }


    @Test
    public void testChercherClients() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(new User(), new User()));
        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testChercherUnClients() throws Exception {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userService.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> foundUsers = userService.findById(userId);
        assertEquals(user, foundUsers);
     verify(userService, times(1)).findById(userId);
    }

    @Test
    public void testCreateUser()  {


        User userSave = new User(1L, "1001L", "John", "Doe", "john.doe@example.com", new Date());
        when(userService.save(any(User.class))).thenReturn(userSave);

// First call
        User user = userService.save(userSave);
        assertEquals(userSave, user);

// Second call
        User userAgain = userService.save(userSave);
        assertEquals(userSave, userAgain);

        verify(userService, times(2)).save(userSave);

    }
    @Test
    void contextLoads() {
        assertNotNull(userService);
    }
}
