package es.plexus.mongodb.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserMongodb{
    @Id
    private long id;
    private String username;
    private String name;
    private String surnames;
    private String email;
    private String password;
    private LocalDate birthdate;
    private LocalDate signUpDate;
    private String description;

    private UserRolMongodb userRol;
}
