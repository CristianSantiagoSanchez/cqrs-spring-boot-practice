package es.plexus.mariadb.repository.user;

import es.plexus.mariadb.user.UserRolMariadb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolMariadbRepository extends JpaRepository<UserRolMariadb, Long> {
    public Optional<UserRolMariadb> findOneById(long id);
    public Optional<UserRolMariadb> findOneByName(String name);
}
