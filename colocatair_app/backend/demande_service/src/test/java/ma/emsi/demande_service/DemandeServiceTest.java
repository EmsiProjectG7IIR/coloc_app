package ma.emsi.demande_service;

import ma.emsi.demande_service.model.Demande;
import ma.emsi.demande_service.model.Offer;
import ma.emsi.demande_service.model.User;
import ma.emsi.demande_service.repository.DemandeRepository;
import ma.emsi.demande_service.service.DemandeService;
import ma.emsi.demande_service.service.OfferService;
import ma.emsi.demande_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DemandeServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private OfferService offerService;

    @Mock
    private DemandeRepository demandeRepository;

    @InjectMocks
    private DemandeService demandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        // Mocking
        User mockUser = new User(); // Create a mock user object
        when(userService.userById(anyLong())).thenReturn(mockUser);

        Offer mockOffer = new Offer(); // Create a mock offer object
        when(offerService.offerById(anyLong())).thenReturn(mockOffer);

        Demande demande = new Demande();
        demande.setDemandeurId(1L);
        demande.setOfferId(2L);

        when(demandeRepository.save(any(Demande.class))).thenReturn(demande);

        // Test the save method
        Demande savedDemande = demandeService.save(demande);

        // Verify that the methods were called with the correct parameters
        verify(userService, times(1)).userById(anyLong());
        verify(offerService, times(1)).offerById(anyLong());
        verify(demandeRepository, times(1)).save(any(Demande.class));

        // Assert that the returned demande is the same as the one saved
        assertEquals(demande, savedDemande);
    }

    // Add more test methods as needed for other methods in DemandeService
}
