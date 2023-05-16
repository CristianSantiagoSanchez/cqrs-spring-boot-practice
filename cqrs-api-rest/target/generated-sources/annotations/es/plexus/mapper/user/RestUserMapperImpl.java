package es.plexus.mapper.user;

import es.plexus.dto.user.UserDto;
import es.plexus.entity.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-16T14:01:07+0200",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class RestUserMapperImpl implements RestUserMapper {

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setName( userDto.getName() );
        user.setSurnames( userDto.getSurnames() );
        user.setEmail( userDto.getEmail() );
        user.setBirthdate( userDto.getBirthdate() );
        user.setSignUpDate( userDto.getSignUpDate() );
        user.setDescription( userDto.getDescription() );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setName( user.getName() );
        userDto.setSurnames( user.getSurnames() );
        userDto.setEmail( user.getEmail() );
        userDto.setBirthdate( user.getBirthdate() );
        userDto.setSignUpDate( user.getSignUpDate() );
        userDto.setDescription( user.getDescription() );

        return userDto;
    }

    @Override
    public List<UserDto> toListDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toLisEntity(List<UserDto> userDtos) {
        if ( userDtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDtos.size() );
        for ( UserDto userDto : userDtos ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }
}
