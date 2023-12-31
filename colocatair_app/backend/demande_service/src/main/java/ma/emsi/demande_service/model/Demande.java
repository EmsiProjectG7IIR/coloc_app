package ma.emsi.demande_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"demande\"")
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long demandeurId;
    @Transient
    @ManyToOne
    private User demandeur;
    private Long offerId;
    @Transient
    @ManyToOne
    private Offer offer;
    private String status;
    @Column(name = "dateCreation")
    private Date dateCreation;


}
