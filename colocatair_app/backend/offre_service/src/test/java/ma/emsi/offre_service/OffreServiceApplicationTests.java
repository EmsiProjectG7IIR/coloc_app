package ma.emsi.offre_service;

import ma.emsi.offre_service.controller.OfferController;
import ma.emsi.offre_service.entites.Offer;
import ma.emsi.offre_service.entites.User;
import ma.emsi.offre_service.repository.OfferRepository;
import ma.emsi.offre_service.service.OfferService;
import ma.emsi.offre_service.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class OffreServiceApplicationTests {
    @Autowired
    OfferService offreService;
    @Autowired
    OfferController offerController;


    private static final String OFFERADDRESS = "Offer Address";
    private static final String TESTOFFER = "Test Offer";
    private static final String OFFERIMAGE = "offer.jpg";
    private static final String DESCRIPTION = "Offer Description";

    @InjectMocks
    private OfferService offerService;

    @Mock
    private OfferRepository offerRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void testSaveOffer() {
//        // Arrange
//        User user = new User(1L, "101L", "John", "Doe", "john.doe@example.com", new Date());
//        Offer offerToSave = new Offer(1L, "101L", user, TESTOFFER,
//                DESCRIPTION, OFFERADDRESS, 100.0, "true", OFFERIMAGE);
//
//        // Mocking the behavior of userService.userById and offerRepository.save
//        when(userService.userById(101L)).thenReturn(user);
//        when(offerRepository.save(offerToSave)).thenReturn(offerToSave);
//
//        // Act
//        Offer savedOffer = offerService.save(offerToSave);
//
//        // Assert
//        assertNotNull(savedOffer);
//        assertEquals(user, savedOffer.getUser());
//        assertEquals(1, savedOffer.getIdCreateur());
//
//        verify(userService, times(1)).userById(101L);
//        verify(offerRepository, times(1)).save(offerToSave);
//    }

   // @Test
//    void testSaveOfferUserNotFound() {
//        // Arrange
//        Offer offerToSave = new Offer(1L, "101L", null, TESTOFFER,
//                DESCRIPTION, OFFERADDRESS, 100.0, "true", "offer1.jpg");
//
//        // Mocking the behavior of userService.userById
//        when(userService.userById(101L)).thenReturn(null);
//
//        // Act
//        Offer savedOffer = offerService.save(offerToSave);
//
//        // Assert
//        assertNull(savedOffer);
//
//        verify(userService, times(1)).userById(101L);
//        verify(offerRepository, never()).save(offerToSave);
//    }

    @Test
    void testDeleteOffer() {
        // Arrange
        Offer offerToDelete = new Offer(1L, "101L", null, TESTOFFER,
                DESCRIPTION, OFFERADDRESS, 100.0, "true", OFFERIMAGE);

        // Act
        offerService.delete(offerToDelete);

        // Assert
        verify(offerRepository, times(1)).delete(offerToDelete);
    }

    @Test
    void testFindOfferById() {
        // Arrange
        long offerId = 1L;
        Offer expectedOffer = new Offer(offerId, "101L", null, TESTOFFER,
                DESCRIPTION, OFFERADDRESS, 100.0, "true", OFFERIMAGE);

        // Mocking the behavior of offerRepository.findById
        when(offerRepository.findById(offerId)).thenReturn(Optional.of(expectedOffer));

        // Act
        Optional<Offer> foundOffer = offerService.findById(offerId);

        // Assert
        assertTrue(foundOffer.isPresent());
        assertEquals(expectedOffer, foundOffer.get());

        verify(offerRepository, times(1)).findById(offerId);
    }

    @Test
    void testFindAllOffers() {
        // Arrange
        List<Offer> offerList = new ArrayList<>();
        offerList.add(new Offer(1L, "101L", null, "Test Offer 1",
                "Offer Description 1", OFFERADDRESS, 100.0, "true", "offer1.jpg"));
        offerList.add(new Offer(2L, "102L", null, "Test Offer 2",
                "Offer Description 2", OFFERADDRESS, 200.0, "false", "offer2.jpg"));

        // Mocking the behavior of offerRepository.findAll
        when(offerRepository.findAll()).thenReturn(offerList);

        // Act
        List<Offer> foundOfferList = offerService.findAll();

        // Assert
        assertEquals(offerList.size(), foundOfferList.size());
        assertTrue(foundOfferList.containsAll(offerList));
        verify(offerRepository, times(1)).findAll();
    }
    @Test
    void contextLoads() {
        assertNotNull(offreService);
        assertNotNull(offerController);
        assertNotNull(userService);
    }

}
