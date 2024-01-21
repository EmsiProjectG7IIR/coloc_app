package ma.emsi.demande_service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import ma.emsi.demande_service.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testNoArgsConstructor() {
        User user = new User();
        // Perform assertions to check default values or behavior
        assertEquals(null, user.getNom());
        assertEquals(null, user.getPrenom());
        // Add more assertions based on your class's default behavior
    }

    @Test
    public void testAllArgsConstructor() {
        User user = new User(1L, 1001L, "John", "Doe", "john.doe@example.com", new Date());
        // Perform assertions to check if values are set correctly
        assertEquals(1L, user.getId());

        assertEquals("John", user.getNom());
        assertEquals("Doe", user.getPrenom());
        assertEquals("john.doe@example.com", user.getEmail());
        // assertEquals(new Date(), user.getDateNaissance());
        // Add more assertions based on your class's behavior
    }
    @Test
    public void testDataAnnotation() {
        User user = new User(1L, 1001L, "John", "Doe", "john.doe@example.com", new Date());
        User anotherUser = new User(1L, 1001L, "John", "Doe", "john.doe@example.com", new Date());

        // Test equals()
        assertEquals(user, anotherUser);

        // Test hashCode()
        assertEquals(user.hashCode(), anotherUser.hashCode());

        // Test toString()
        assertEquals(user.toString(), anotherUser.toString());
    }



}
