package ma.emsi.demande_service;

import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferTest {

    @Test
    public void testNoArgsConstructor() {
        Offer offer = new Offer();
        // Perform assertions to check default values or behavior
        assertEquals(null, offer.getTitre());
        assertEquals(0.0, offer.getMontant());
        // Add more assertions based on your class's default behavior
    }

    @Test
    public void testAllArgsConstructor() {
        Offer offer = new Offer(1L, 101L, new User(), "Job Offer", new Date(), new Date(), new Date(), "Job Description", "Company Address", 5000.0, true, "offer.jpg");
        // Perform assertions to check if values are set correctly
        assertEquals(1L, offer.getId());
        assertEquals(101L, offer.getIdCreateur());
        // Assuming you have a User object for user
        // assertEquals(new User(), offer.getUser());
        assertEquals("Job Offer", offer.getTitre());
        //assertEquals(new Date(), offer.getDateCreation());
        // assertEquals(new Date(), offer.getDateDebut());
        // assertEquals(new Date(), offer.getDateFin());
        assertEquals("Job Description", offer.getDescription());
        assertEquals("Company Address", offer.getAdresse());
        assertEquals(5000.0, offer.getMontant());
        assertEquals(true, offer.isStatus());
        assertEquals("offer.jpg", offer.getPhoto());
        // Add more assertions based on your class's behavior
    }

    @Test
    public void testDataAnnotation() {
        Offer offer = new Offer(1L, 101L, new User(), "Job Offer", new Date(), new Date(), new Date(), "Job Description", "Company Address", 5000.0, true, "offer.jpg");
        Offer anotherOffer = new Offer(1L, 101L, new User(), "Job Offer", new Date(), new Date(), new Date(), "Job Description", "Company Address", 5000.0, true, "offer.jpg");

        // Test equals()
        assertEquals(offer, anotherOffer);

        // Test hashCode()
        assertEquals(offer.hashCode(), anotherOffer.hashCode());

        // Test toString()
        assertEquals(offer.toString(), anotherOffer.toString());
    }
}
