package es.plexus.repository.user;

import es.plexus.jpa.user.UserRolJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolJpaRepository extends JpaRepository<UserRolJpa, Long> {
    public Optional<UserRolJpa> findOneById(long id);
    public Optional<UserRolJpa> findOneByName(String name);
}
