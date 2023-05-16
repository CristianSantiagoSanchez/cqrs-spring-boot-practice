package es.plexus.mapper.user;

import es.plexus.dto.user.UserDto;
import es.plexus.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RestUserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
    List<UserDto> toListDto(List<User> users);
    List<User> toLisEntity(List<UserDto> userDtos);


}
