package es.plexus.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String name;
    private String surnames;
    private String email;
    private String password;
    private LocalDate birthdate;
    private LocalDate signUpDate;
    private String description;
    private UserRol userRol;
}
