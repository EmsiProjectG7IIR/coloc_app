package ma.emsi.demande_service.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private Long id;
    private Long id_createur;
    private User user;
    private String titre;
    private Date dateCreation;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private String adresse;
    private double montant;
    private boolean status;
    private String photo;
}
