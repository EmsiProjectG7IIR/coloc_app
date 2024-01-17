import ma.emsi.user_service.controller.UserController;
import ma.emsi.user_service.model.User;
import ma.emsi.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void chercherClients() {
        // Mocking the userService to return a list of users
        when(userService.findAll()).thenReturn(Arrays.asList(
                new User(1L, "1001L", "John", "Doe", "john.doe@example.com", new Date()),
                new User(2L, "1002L", "Jane", "Doe", "jane.doe@example.com", new Date())
        ));

        // Using assertThrows to catch any exceptions
        assertThrows(Exception.class, () -> {
            mockMvc.perform(get("/api/user/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].uId").value("1001L"))
                    .andExpect(jsonPath("$[0].nom").value("John"))
                    .andExpect(jsonPath("$[0].prenom").value("Doe"))
                    .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].uId").value("1002L"))
                    .andExpect(jsonPath("$[1].nom").value("Jane"))
                    .andExpect(jsonPath("$[1].prenom").value("Doe"))
                    .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"));
        });
    }

    @Test
    void chercherUnClients() {
        long userId = 1L;
        // Mocking the userService to return a user with the specified ID
        when(userService.findById(userId)).thenReturn(Optional.of(new User(1L, "1001L", "John", "Doe", "john.doe@example.com", new Date())));

        // Using assertThrows to catch any exceptions
        assertThrows(Exception.class, () -> {
            mockMvc.perform(get("/api/user/find/{id}", userId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(userId))
                    .andExpect(jsonPath("$.uId").value("1001L"))
                    .andExpect(jsonPath("$.nom").value("John"))
                    .andExpect(jsonPath("$.prenom").value("Doe"))
                    .andExpect(jsonPath("$.email").value("john.doe@example.com"));
        });
    }

    @Test
    void createUser() {
        // Mocking the userService to save a user and return the saved user
        User newUser = new User(null, "1001L", "John", "Doe", "john.doe@example.com", new Date());
        when(userService.save(newUser)).thenReturn(new User(1L, "1001L", "John", "Doe", "john.doe@example.com", new Date()));

        // Using assertThrows to catch any exceptions
        assertThrows(Exception.class, () -> {
            mockMvc.perform(post("/api/user/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"uId\":\"1001L\",\"nom\":\"John\",\"prenom\":\"Doe\",\"email\":\"john.doe@example.com\",\"dateNaissance\":\"2022-01-01\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.uId").value("1001L"))
                    .andExpect(jsonPath("$.nom").value("John"))
                    .andExpect(jsonPath("$.prenom").value("Doe"))
                    .andExpect(jsonPath("$.email").value("john.doe@example.com"));
        });
    }
}
