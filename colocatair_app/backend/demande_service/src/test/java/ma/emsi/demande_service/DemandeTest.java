package ma.emsi.demande_service;
import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemandeTest {

    @Test
    public void testNoArgsConstructor() {
        Demande demande = new Demande();
        // Perform assertions to check default values or behavior
        assertEquals(null, demande.getStatus());
        // assertEquals(null, demande.getDateCreation());
        // Add more assertions based on your class's default behavior
    }

    @Test
    public void testAllArgsConstructor() {
        Demande demande = new Demande(1L, 201L, new User(), 101L, new Offer(), "Pending", new Date());
        // Perform assertions to check if values are set correctly
        assertEquals(1L, demande.getId());
        assertEquals(201L, demande.getDemandeurId());
        // Assuming you have a User object for demandeur
        // assertEquals(new User(), demande.getDemandeur());
        assertEquals(101L, demande.getOfferId());
        // Assuming you have an Offer object for offer
        // assertEquals(new Offer(), demande.getOffer());
        assertEquals("Pending", demande.getStatus());
    //    assertEquals(new Date(), demande.getDateCreation());
        // Add more assertions based on your class's behavior
    }

    @Test
    public void testDataAnnotation() {
        Demande demande = new Demande(1L, 201L, new User(), 101L, new Offer(), "Pending", new Date());
        Demande anotherDemande = new Demande(1L, 201L, new User(), 101L, new Offer(), "Pending", new Date());

        // Test equals()
        assertEquals(demande, anotherDemande);

        // Test hashCode()
        assertEquals(demande.hashCode(), anotherDemande.hashCode());

        // Test toString()
        assertEquals(demande.toString(), anotherDemande.toString());
    }
}
