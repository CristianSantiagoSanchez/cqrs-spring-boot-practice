package es.plexus.mapper.user;

import es.plexus.dto.user.UserDto;
import es.plexus.dto.user.UserPostDto;
import es.plexus.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestUserPostMapper {
    User toEntity(UserPostDto userPostDto);
    UserPostDto toDto(User user);
    List<UserPostDto> toListDto(List<User> users);
    List<User> toLisEntity(List<UserPostDto> userPostDtos);

}
