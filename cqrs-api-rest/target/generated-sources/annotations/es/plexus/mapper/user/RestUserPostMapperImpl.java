package es.plexus.mapper.user;

import es.plexus.dto.user.UserPostDto;
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
public class RestUserPostMapperImpl implements RestUserPostMapper {

    @Override
    public User toEntity(UserPostDto userPostDto) {
        if ( userPostDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userPostDto.getUsername() );
        user.setName( userPostDto.getName() );
        user.setSurnames( userPostDto.getSurnames() );
        user.setEmail( userPostDto.getEmail() );
        user.setPassword( userPostDto.getPassword() );
        user.setBirthdate( userPostDto.getBirthdate() );
        user.setDescription( userPostDto.getDescription() );

        return user;
    }

    @Override
    public UserPostDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserPostDto userPostDto = new UserPostDto();

        userPostDto.setUsername( user.getUsername() );
        userPostDto.setName( user.getName() );
        userPostDto.setSurnames( user.getSurnames() );
        userPostDto.setEmail( user.getEmail() );
        userPostDto.setPassword( user.getPassword() );
        userPostDto.setBirthdate( user.getBirthdate() );
        userPostDto.setDescription( user.getDescription() );

        return userPostDto;
    }

    @Override
    public List<UserPostDto> toListDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserPostDto> list = new ArrayList<UserPostDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toLisEntity(List<UserPostDto> userPostDtos) {
        if ( userPostDtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userPostDtos.size() );
        for ( UserPostDto userPostDto : userPostDtos ) {
            list.add( toEntity( userPostDto ) );
        }

        return list;
    }
}
