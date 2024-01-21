package ma.emsi.demande_service;

import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import ma.emsi.demande_service.repository.DemandeRepository;
import ma.emsi.demande_service.service.DemandeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DemandeServiceApplicationTests {

	@Mock
	private DemandeRepository demandeRepository;



	@Test
	public void testUserGettersAndSetters() {
		// Create a User object
		User user = new User();

		// Set values using setters
		user.setId(1L);

		user.setNom("John");
		user.setPrenom("Doe");
		user.setEmail("john.doe@example.com");


		// Test getters
		assertEquals(1L, user.getId());

		assertEquals("John", user.getNom());
		assertEquals("Doe", user.getPrenom());
		assertEquals("john.doe@example.com", user.getEmail());

	}

	@Test
	public void testOfferGettersAndSetters() {
		// Create an Offer object
		Offer offer = new Offer();

		// Set values using setters
		offer.setId(1L);
		offer.setIdCreateur(101L);
		// Assuming you have a User object for user

		offer.setTitre("Job Offer");
		offer.setDateCreation(new Date());
		offer.setDateDebut(new Date());
		offer.setDateFin(new Date());
		offer.setDescription("Job Description");
		offer.setAdresse("Company Address");
		offer.setMontant(5000.0);
		offer.setStatus(true);
		offer.setPhoto("offer.jpg");

		// Test getters
		assertEquals(1L, offer.getId());
		assertEquals(101L, offer.getIdCreateur());
		// Uncomment and adjust accordingly if you have a User object
		// assertEquals(user, offer.getUser());
		assertEquals("Job Offer", offer.getTitre());
		assertEquals("Job Description", offer.getDescription());
		assertEquals("Company Address", offer.getAdresse());
		assertEquals(5000.0, offer.getMontant());
		assertEquals(true, offer.isStatus());
		assertEquals("offer.jpg", offer.getPhoto());
	}
	@Test
	public void testGettersAndSetters() {
		// Create a Demande object
		Demande demande = new Demande();

		// Set values using setters
		demande.setId(1L);
		demande.setDemandeurId(101L);
		// Assuming you have a User object for demandeur

		demande.setOfferId(201L);
		// Assuming you have an Offer object for offer

		demande.setStatus("Pending");
		demande.setDateCreation(new Date());

		// Test getters
		assertEquals(1L, demande.getId());
		assertEquals(101L, demande.getDemandeurId());
		// Uncomment and adjust accordingly if you have a User object
		assertEquals(201L, demande.getOfferId());
		// Uncomment and adjust accordingly if you have an Offer object
		assertEquals("Pending", demande.getStatus());
		// assertEquals(new Date(), demande.getDateCreation());

		// Optionally, you can test additional assertions based on your specific use case
	}

	@InjectMocks
	private DemandeService demandeService;

	@Test
	void testFindAllDemandes() {
		// Mock data
		User user1 = new User(/* user1 details */);
		User user2 = new User(/* user2 details */);
		Offer offer1 = new Offer(/* offer1 details */);
		Offer offer2 = new Offer(/* offer2 details */);

		Demande demande1 = new Demande(1L, 101L, user1, 201L, offer1, "Demande 1", new Date());
		Demande demande2 = new Demande(2L, 102L, user2, 202L, offer2, "Demande 2", new Date());

		when(demandeRepository.findAll()).thenReturn(Arrays.asList(demande1, demande2));

		// Perform the service method
		List<Demande> demandes = demandeService.findAll();


		// assertNotNull(demandes.get(1).getDateCreation());

		// Verify that the demandeRepository.findAll() method was called once
		verify(demandeRepository, times(1)).findAll();
	}


	@Test
	void contextLoads() {

	}

}
