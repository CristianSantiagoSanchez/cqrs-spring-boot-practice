package es.plexus.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPatchDto {
    @Size(min = 1)
    private String username;
    @Size(min = 1)
    private String name;
    @Size(min = 1)
    private String surnames;
    @Email(message = "Invalid email format")
    @Size(min = 1)
    private String email;
    @Past
    @DateTimeFormat
    private LocalDate birthdate;
    @Size(max = 150)
    private String description;
}