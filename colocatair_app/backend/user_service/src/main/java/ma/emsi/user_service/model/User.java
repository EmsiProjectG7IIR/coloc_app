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
    @SuppressWarnings("unused")
    @Column(name = "uid", unique = true, nullable = false)
    private String uid;
    @SuppressWarnings("unused")
    private String nom;
    @SuppressWarnings("unused")
    private String prenom;

    @SuppressWarnings("unused")
    private String email;
    @Column(name = "date_naissance")
    private Date dateNaissance;

}