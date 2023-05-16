package es.plexus.mapper.user;

import es.plexus.entity.user.UserRol;
import es.plexus.jpa.user.UserRolJpa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:00:47+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class UserRolMapperImpl implements UserRolMapper {

    @Override
    public UserRolJpa toJpa(UserRol userRol) {
        if ( userRol == null ) {
            return null;
        }

        UserRolJpa userRolJpa = new UserRolJpa();

        userRolJpa.setId( userRol.getId() );
        userRolJpa.setName( userRol.getName() );

        return userRolJpa;
    }

    @Override
    public UserRol toEntity(UserRolJpa userRolJpa) {
        if ( userRolJpa == null ) {
            return null;
        }

        UserRol userRol = new UserRol();

        userRol.setId( userRolJpa.getId() );
        userRol.setName( userRolJpa.getName() );

        return userRol;
    }
}
