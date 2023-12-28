package ma.emsi.demande_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private Long uId;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
}
