package ma.emsi.user_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.emsi.user_service.controller.UserController;
import ma.emsi.user_service.model.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import ma.emsi.user_service.repository.UserRepository;
import ma.emsi.user_service.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceApplicationTests {

    @Mock
    private DiscoveryClient discoveryClient;
    @SuppressWarnings("unused")
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserController userController;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    }
    @Test
    @Transactional
    void testFindAll() {

        List<User> result = userService.findAll();

        Assert.assertEquals(0, result.size());
    }

    @Test
    @Transactional
    void testSave() {
        User user = new User();
        user.setId(3L);
        user.setEmail("maryem31@gmail.com");
        user.setNom("Mallouli");
        user.setPrenom("Aimrane");
        user.setDateNaissance(new Date());

        userService.save(user);
        Optional<User> saveduser = userRepository.findById(user.getId());
        Assert.assertNotNull(saveduser);
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
    @Transactional
    void testFindById() {
        User user = new User();
        user.setUid("2L");
        user.getEmail();
        user.getNom();
        user.getPrenom();
        // user.getDateNaissance();
        userRepository.save(user);

        Optional<User> result = userService.findById(user.getId());

        Assert.assertNotNull(result);
    }
    @Test
    void contextLoads() {
        assertNotNull(discoveryClient);


    }
}
