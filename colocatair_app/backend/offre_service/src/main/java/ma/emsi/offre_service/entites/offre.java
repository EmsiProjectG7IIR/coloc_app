package ma.emsi.offre_service.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Long idCreateur;
    @Transient
    @ManyToOne
    private User user;
    //@NotBlank(message = "Le titre ne peut pas être vide")
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
