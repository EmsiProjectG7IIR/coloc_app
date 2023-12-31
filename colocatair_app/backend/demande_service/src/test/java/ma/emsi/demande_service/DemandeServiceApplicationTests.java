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


		assertNotNull(demandes.get(1).getDateCreation());

		// Verify that the demandeRepository.findAll() method was called once
		verify(demandeRepository, times(1)).findAll();
	}


	@Test
	void contextLoads() {
		assertNotNull(demandeService);
	}

}
