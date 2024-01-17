package ma.emsi.demande_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @SuppressWarnings("unused")private Long id;
    @SuppressWarnings("unused")private Long idCreateur;
    @SuppressWarnings("unused")private User user;
    @SuppressWarnings("unused")private String titre;
    @SuppressWarnings("unused")private Date dateCreation;
    @SuppressWarnings("unused")private Date dateDebut;
    @SuppressWarnings("unused")private Date dateFin;
    @SuppressWarnings("unused")private String description;
    @SuppressWarnings("unused")private String adresse;
    @SuppressWarnings("unused")private double montant;
    @SuppressWarnings("unused")private boolean status;
    @SuppressWarnings("unused")private String photo;
}
