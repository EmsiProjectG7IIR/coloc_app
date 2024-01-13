package ma.emsi.user_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uId='" + uId + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
