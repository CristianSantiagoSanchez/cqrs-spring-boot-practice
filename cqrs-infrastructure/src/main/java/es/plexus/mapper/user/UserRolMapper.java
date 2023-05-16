package es.plexus.mapper.user;

import es.plexus.entity.user.UserRol;
import es.plexus.jpa.user.UserRolJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRolMapper {
    UserRolJpa toJpa(UserRol userRol);
    UserRol toEntity(UserRolJpa userRolJpa);
}
