package es.plexus.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPostDto {

    @NotEmpty(message = "Username is empty")
    private String username;

    @NotEmpty(message = "Name is empty")
    private String name;

    @NotEmpty(message = "Surnames are empty")
    private String surnames;

    @Email(message = "Invalid email format")
    @NotEmpty
    private String email;

    @Size(min = 6, max = 20, message = "Invalid password ")
    @NotEmpty
    private String password;

    @Past
    private LocalDate birthdate;

    @Size(max = 150)
    private String description;
}
