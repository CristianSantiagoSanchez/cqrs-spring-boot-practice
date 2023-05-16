package es.plexus.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.query.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:00:57+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class UserResponseMapperImpl implements UserResponseMapper {

    @Override
    public UserResponse entityToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setName( user.getName() );
        userResponse.setSurnames( user.getSurnames() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setBirthdate( user.getBirthdate() );
        userResponse.setSignUpDate( user.getSignUpDate() );
        userResponse.setDescription( user.getDescription() );

        return userResponse;
    }

    @Override
    public List<UserResponse> entityToLIstUserResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( entityToUserResponse( user ) );
        }

        return list;
    }
}
