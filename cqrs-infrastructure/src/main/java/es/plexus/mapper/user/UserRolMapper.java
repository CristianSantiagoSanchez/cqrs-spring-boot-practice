package es.plexus.mapper.user;

import es.plexus.entity.user.UserRol;
import es.plexus.mariadb.user.UserRolMariadb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRolMapper {
    UserRolMariadb toJpa(UserRol userRol);
    UserRol toEntity(UserRolMariadb userRolJpa);
}
