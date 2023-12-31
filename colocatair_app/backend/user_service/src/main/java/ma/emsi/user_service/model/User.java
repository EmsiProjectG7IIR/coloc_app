package ma.emsi.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "u_id", unique = true, nullable = false)
    private String uId;
    private String nom;
    private String prenom;
    private String email;
    @Column(name = "date_naissance")
    private Date dateNaissance;
}
