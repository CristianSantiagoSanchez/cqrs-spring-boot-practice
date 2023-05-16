package es.plexus.repository.user;


import es.plexus.jpa.user.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa, Long>, JpaSpecificationExecutor<UserJpa>{
    public List<UserJpa> findAll();
    public Optional<UserJpa> findByEmail(String email);
    public Optional<UserJpa> findOneByUsername(String username);
    public List<UserJpa> findByUsername(String username);
    public Optional<UserJpa> findByUsernameAndPassword(String username, String password);
}
