package es.plexus.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.query.user.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponse entityToUserResponse(User user);
    User responseToUserEntity(UserResponse userResponse);
    List<UserResponse> entityToLIstUserResponse(List<User> users);
}
