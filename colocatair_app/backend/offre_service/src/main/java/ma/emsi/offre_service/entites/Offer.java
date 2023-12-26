package ma.emsi.offre_service.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_createur;
    @Transient
    @ManyToOne
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
