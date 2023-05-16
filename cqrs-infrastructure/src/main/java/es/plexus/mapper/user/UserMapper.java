package es.plexus.mapper.user;

import es.plexus.entity.user.User;
import es.plexus.jpa.user.UserJpa;
import es.plexus.mapper.book.BookMapper;
import es.plexus.mapper.swap.SwapMapper;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserJpa toJpa(User user);
    User toEntity(UserJpa userJpa);
    List<UserJpa> toListJpa(List<User> users);
    List<User> toListEntity(List<UserJpa> userJpas);
}
