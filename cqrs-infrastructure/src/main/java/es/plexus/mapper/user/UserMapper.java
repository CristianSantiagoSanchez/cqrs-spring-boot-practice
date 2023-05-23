package es.plexus.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.mariadb.user.UserMariadb;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMariadb toJpa(User user);
    User toEntity(UserMariadb userJpa);
    List<UserMariadb> toListJpa(List<User> users);
    List<User> toListEntity(List<UserMariadb> userJpas);
}
