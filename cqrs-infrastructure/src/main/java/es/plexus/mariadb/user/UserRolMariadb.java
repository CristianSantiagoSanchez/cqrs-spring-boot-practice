package es.plexus.mariadb.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRolMariadb {
    @Id
    private long id;
    @Column(nullable = false, unique = true)
    private String name;


}
