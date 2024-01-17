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
    private String idCreateur;
    @Transient
    @ManyToOne
    private User user;
    private String titre;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private String adresse;
    private double montant;
    private String status;
    private String photo;

    public Offer(Long id, String idCreateur, User user, String titre, String description, String adresse, double montant, String status, String photo) {
        this.id = id;
        this.idCreateur = idCreateur;
        this.user = user;
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
        this.montant = montant;
        this.status = status;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", idCreateur=" + idCreateur +
                ", user=" + user +
                ", titre='" + titre + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", description='" + description + '\'' +
                ", adresse='" + adresse + '\'' +
                ", montant=" + montant +
                ", status=" + status +
                ", photo='" + photo + '\'' +
                '}';
    }
}