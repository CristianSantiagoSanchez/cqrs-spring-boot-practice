package es.plexus.repository.user.command;

import es.plexus.entity.user.UserRol;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolCommandRepository {
    public UserRol createRol(UserRol userRol);
    public UserRol findOneByName(String name);
    public UserRol findOneById(long id);
}
