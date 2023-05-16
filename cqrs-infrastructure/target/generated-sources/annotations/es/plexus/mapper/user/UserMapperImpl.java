package es.plexus.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.entity.user.UserRol;
import es.plexus.jpa.user.UserJpa;
import es.plexus.jpa.user.UserRolJpa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:00:47+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserJpa toJpa(User user) {
        if ( user == null ) {
            return null;
        }

        UserJpa userJpa = new UserJpa();

        userJpa.setId( user.getId() );
        userJpa.setUsername( user.getUsername() );
        userJpa.setName( user.getName() );
        userJpa.setSurnames( user.getSurnames() );
        userJpa.setEmail( user.getEmail() );
        userJpa.setPassword( user.getPassword() );
        userJpa.setBirthdate( user.getBirthdate() );
        userJpa.setSignUpDate( user.getSignUpDate() );
        userJpa.setDescription( user.getDescription() );
        userJpa.setUserRol( userRolToUserRolJpa( user.getUserRol() ) );

        return userJpa;
    }

    @Override
    public User toEntity(UserJpa userJpa) {
        if ( userJpa == null ) {
            return null;
        }

        User user = new User();

        user.setId( userJpa.getId() );
        user.setUsername( userJpa.getUsername() );
        user.setName( userJpa.getName() );
        user.setSurnames( userJpa.getSurnames() );
        user.setEmail( userJpa.getEmail() );
        user.setPassword( userJpa.getPassword() );
        user.setBirthdate( userJpa.getBirthdate() );
        user.setSignUpDate( userJpa.getSignUpDate() );
        user.setDescription( userJpa.getDescription() );
        user.setUserRol( userRolJpaToUserRol( userJpa.getUserRol() ) );

        return user;
    }

    @Override
    public List<UserJpa> toListJpa(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserJpa> list = new ArrayList<UserJpa>( users.size() );
        for ( User user : users ) {
            list.add( toJpa( user ) );
        }

        return list;
    }

    @Override
    public List<User> toListEntity(List<UserJpa> userJpas) {
        if ( userJpas == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userJpas.size() );
        for ( UserJpa userJpa : userJpas ) {
            list.add( toEntity( userJpa ) );
        }

        return list;
    }

    protected UserRolJpa userRolToUserRolJpa(UserRol userRol) {
        if ( userRol == null ) {
            return null;
        }

        UserRolJpa userRolJpa = new UserRolJpa();

        userRolJpa.setId( userRol.getId() );
        userRolJpa.setName( userRol.getName() );

        return userRolJpa;
    }

    protected UserRol userRolJpaToUserRol(UserRolJpa userRolJpa) {
        if ( userRolJpa == null ) {
            return null;
        }

        UserRol userRol = new UserRol();

        userRol.setId( userRolJpa.getId() );
        userRol.setName( userRolJpa.getName() );

        return userRol;
    }
}
