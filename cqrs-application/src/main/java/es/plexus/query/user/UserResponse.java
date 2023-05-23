package es.plexus.query.user;

import es.plexus.entity.user.UserRol;
import es.plexus.shared.bus.query.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserResponse implements Response {
    private long id;
    private String username;
    private String name;
    private String surnames;
    private String email;
    private LocalDate birthdate;
    private LocalDate signUpDate;
    private String description;
    private UserRol userRol;

}